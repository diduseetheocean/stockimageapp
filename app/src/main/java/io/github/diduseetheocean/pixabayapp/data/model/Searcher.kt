package io.github.diduseetheocean.pixabayapp.data.model

import io.github.diduseetheocean.pixabayapp.data.repository.ImagesSearchRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.onStart
import javax.inject.Inject

class Searcher @Inject constructor(
    private val imagesRepository: ImagesSearchRepository
) {
    operator fun invoke(searchQuery: String): Flow<SearchState> {
        return imagesRepository.search(searchQuery)
            .map { if (it.isEmpty()) SearchState.Empty else SearchState.Success(it) }
            .onStart { emit(SearchState.Loading) }
            .catch { emit(SearchState.Error(it)) }
    }
}

sealed class SearchState {
    object Empty : SearchState()
    class Success(val images: List<ImageModel>) : SearchState()
    object Loading : SearchState()
    class Error(val throwable: Throwable) : SearchState()
}