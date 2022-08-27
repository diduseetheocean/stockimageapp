package io.github.diduseetheocean.stockimageapp.data.model

import io.github.diduseetheocean.stockimageapp.data.model.ApiType.PIXABAY
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchFlickrRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPexelsRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPixabayRepositoryInterface
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class SearcherTest {

    @Test
    fun `search Should return SearchState Loading`() = runBlocking {
        val hit = mockk<ImageModel>()
        val repositoryPixabay = mockPixabayRepository(flowOf(listOf(hit, hit, hit)))
        val repositoryPexels = mockPexelsRepository(flowOf(listOf(hit, hit, hit)))
        val repositoryFlickr = mockFlickrRepository(flowOf(listOf(hit, hit, hit)))

        val result =
            Searcher(repositoryPixabay, repositoryPexels, repositoryFlickr).invoke("flowers", PIXABAY).first()

        assert((result is SearchState.Loading))
    }

    @Test
    fun `search Should return Success state`() = runBlocking {
        val repositoryPixabay = mockPixabayRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })
        val repositoryPexels = mockPexelsRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })
        val repositoryFlickr = mockFlickrRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })

        val result = Searcher(repositoryPixabay, repositoryPexels, repositoryFlickr).invoke("flowers", PIXABAY).last()

        assert((result is SearchState.Success) && result.images.size == 3)
    }

    @Test
    fun `search Should return Loading then Success`() = runBlocking {
        val repositoryPixabay = mockPixabayRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })
        val repositoryPexels = mockPexelsRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })
        val repositoryFlickr = mockFlickrRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })

        val result =
            Searcher(repositoryPixabay, repositoryPexels, repositoryFlickr).invoke("flowers", PIXABAY).toList()
        assert(result.first() is SearchState.Loading)
        assert(result.drop(1).first() is SearchState.Success)
        assert(result.count() == 2)
    }

    @Test
    fun `search with empty results Should return SearchState Empty`() = runBlocking {
        val repositoryPixabay = mockPixabayRepository(flowOf(emptyList()))
        val repositoryPexels = mockPexelsRepository(flowOf(emptyList()))
        val repositoryFlickr = mockFlickrRepository(flowOf(emptyList()))

        val result = Searcher(repositoryPixabay, repositoryPexels, repositoryFlickr).invoke("flowers", PIXABAY).last()

        assert((result is SearchState.Empty))
    }

    @Test
    fun `search with exception Should return SearchState Error`() = runBlocking {
        val repositoryPixabay = mockPixabayRepository(flow {
            throw UnknownHostException()
        })
        val repositoryPexels = mockPexelsRepository(flow {
            throw UnknownHostException()
        })
        val repositoryFlickr = mockFlickrRepository(flow {
            throw UnknownHostException()
        })

        val result = Searcher(repositoryPixabay, repositoryPexels, repositoryFlickr).invoke("flowers", PIXABAY).last()

        assert((result is SearchState.Error) && result.throwable is UnknownHostException)
    }

    private val imageModelDummy = ImageModel(
        userName = "",
        comments = "",
        downloads = "",
        largeImageURL = "",
        likes = "",
        tags = emptyList(),
        url = "",
        userImageURL = ""
    )

    private fun mockPixabayRepository(flowReturn: Flow<List<ImageModel>>) =
        object : ImagesSearchPixabayRepositoryInterface {
            override fun search(query: String): Flow<List<ImageModel>> = flowReturn
        }

    private fun mockPexelsRepository(flowReturn: Flow<List<ImageModel>>) =
        object : ImagesSearchPexelsRepositoryInterface {
            override fun search(query: String): Flow<List<ImageModel>> = flowReturn
        }

    private fun mockFlickrRepository(flowReturn: Flow<List<ImageModel>>) =
        object : ImagesSearchFlickrRepositoryInterface {
            override fun search(query: String): Flow<List<ImageModel>> = flowReturn
        }
}