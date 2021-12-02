package io.github.diduseetheocean.stockimageapp.data.model

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import io.github.diduseetheocean.stockimageapp.data.model.ApiType.PIXABAY
import io.github.diduseetheocean.stockimageapp.data.model.SearchState.Empty
import io.github.diduseetheocean.stockimageapp.data.model.SearchState.Success
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import javax.inject.Inject

@HiltViewModel
class SearchImagesViewModel @Inject constructor(
    val searcher: Searcher,
) : ViewModel() {

    private var _searchViewState = MutableStateFlow<SearchState>(Empty)
    val searchViewState: StateFlow<SearchState> = _searchViewState

    private var _searchQueryState = MutableStateFlow("fruits")
    val searchQueryState: StateFlow<String> = _searchQueryState

    private var _searchApiType = MutableStateFlow(PIXABAY)
    val searchApiType: StateFlow<ApiType> = _searchApiType

    init {
        onSearchClicked()
    }

    private fun search(searchQuery: String) {
        searcher(
            searchQuery = searchQuery,
            apiType = searchApiType.value
        )
            .onEach { _searchViewState.value = it }
            .launchIn(viewModelScope)
    }

    fun onSearchClicked() {
        search(_searchQueryState.value)
    }

    fun onSearchChange(searchText: String) {
        _searchQueryState.value = searchText
    }

    fun onApicChange(apiType: ApiType) {
        _searchApiType.value = apiType
    }

    fun findImage(id: Long): ImageModel? =
        (_searchViewState.value as? Success)?.images?.find { it.imageId == id }
}