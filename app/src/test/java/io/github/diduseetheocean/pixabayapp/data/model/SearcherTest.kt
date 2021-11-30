package io.github.diduseetheocean.pixabayapp.data.model

import io.github.diduseetheocean.pixabayapp.data.repository.ImagesSearchRepository
import io.mockk.mockk
import kotlinx.coroutines.flow.*
import kotlinx.coroutines.runBlocking
import org.junit.Test
import java.net.UnknownHostException

class SearcherTest {

    @Test
    fun `search Should return SearchState Loading`() = runBlocking {
        val hit = mockk<ImageModel>()
        val repository = mockRepository(flowOf(listOf(hit, hit, hit)))

        val result = Searcher(repository).invoke("flowers").first()

        assert((result is SearchState.Loading))
    }

    @Test
    fun `search Should return Success state`() = runBlocking {
        val repository = mockRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })

        val result = Searcher(repository).invoke("flowers").last()

        assert((result is SearchState.Success) && result.images.size == 3)
    }

    @Test
    fun `search Should return Loading then Success`() = runBlocking {
        val repository = mockRepository(flow {
            emit(listOf(imageModelDummy, imageModelDummy, imageModelDummy))
        })

        val result = Searcher(repository).invoke("flowers").toList()
        assert(result.first() is SearchState.Loading)
        assert(result.drop(1).first() is SearchState.Success)
        assert(result.count() == 2)
    }

    @Test
    fun `search with empty results Should return SearchState Empty`() = runBlocking {
        val repository = mockRepository(flowOf(emptyList()))

        val result = Searcher(repository).invoke("flowers").last()

        assert((result is SearchState.Empty))
    }

    @Test
    fun `search with exception Should return SearchState Error`() = runBlocking {
        val repository = mockRepository(flow {
            throw UnknownHostException()
        })

        val result = Searcher(repository).invoke("flowers").last()

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

    private fun mockRepository(flowReturn: Flow<List<ImageModel>>) =
        object : ImagesSearchRepository {
            override fun search(query: String): Flow<List<ImageModel>> = flowReturn
        }
}