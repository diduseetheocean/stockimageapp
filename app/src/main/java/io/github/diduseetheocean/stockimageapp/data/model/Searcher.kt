package io.github.diduseetheocean.stockimageapp.data.model

import io.github.diduseetheocean.stockimageapp.data.model.ApiType.*
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchFlickrRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPexelsRepositoryInterface
import io.github.diduseetheocean.stockimageapp.data.repository.ImagesSearchPixabayRepositoryInterface
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class Searcher @Inject constructor(
    private val imagesPixabayRepository: ImagesSearchPixabayRepositoryInterface,
    private val imagesPexelsRepository: ImagesSearchPexelsRepositoryInterface,
    private val imagesFlickrRepository: ImagesSearchFlickrRepositoryInterface,
) {
    operator fun invoke(searchQuery: String, apiType: ApiType): Flow<SearchState> {
        return when (apiType) {
            PIXABAY -> imagesPixabayRepository.search(searchQuery).process()
            PEXELS -> imagesPexelsRepository.search(searchQuery).process()
            FLICKR -> imagesFlickrRepository.search(searchQuery).process()
        }
    }
}

private fun Flow<List<ImageModel>>.process() = run {
    this.map { if (it.isEmpty()) SearchState.Empty else SearchState.Success(it) }
        .onStart { emit(SearchState.Loading) }
        .catch { emit(SearchState.Error(it)) }
}

sealed class SearchState {
    object Empty : SearchState()
    class Success(val images: List<ImageModel>) : SearchState()
    object Loading : SearchState()
    class Error(val throwable: Throwable) : SearchState()
}

enum class ApiType {
    PIXABAY,
    PEXELS,
    FLICKR
}