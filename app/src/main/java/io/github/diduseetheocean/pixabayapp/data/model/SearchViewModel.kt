package io.github.diduseetheocean.pixabayapp.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import javax.inject.Inject
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Empty
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Success
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@HiltViewModel
class SearchImagesViewModel @Inject constructor(
    val searcher: Searcher,
) : ViewModel() {

    private var _searchViewState = MutableStateFlow<SearchState>(Empty)
    val searchViewState: StateFlow<SearchState> = _searchViewState

    private var _searchQueryState = MutableStateFlow("fruits")
    val searchQueryState: StateFlow<String> = _searchQueryState

    init {
        onSearchClicked()
    }

    private fun search(searchQuery: String) {
        searcher(searchQuery = searchQuery)
            .onEach { _searchViewState.value = it }
            .launchIn(viewModelScope)
    }

    fun onSearchClicked() {
        search(_searchQueryState.value)
    }

    fun onSearchChange(searchText: String) {
        _searchQueryState.value = searchText
    }

    fun findImage(id: Long): ImageModel? =
        (_searchViewState.value as? Success)?.images?.find { it.imageId == id }
}