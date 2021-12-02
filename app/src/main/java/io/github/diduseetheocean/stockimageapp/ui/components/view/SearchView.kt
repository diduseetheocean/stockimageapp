package io.github.diduseetheocean.stockimageapp.ui.components.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.diduseetheocean.stockimageapp.data.model.SearchImagesViewModel
import io.github.diduseetheocean.stockimageapp.ui.components.misc.SearchViewContent

@Composable
fun SearchView(viewModel: SearchImagesViewModel, onNavigateClicked: (Long) -> Unit = { }) {
    val viewState by viewModel.searchViewState.collectAsState()
    val query by viewModel.searchQueryState.collectAsState()

    SearchViewContent(
        onImageClicked = onNavigateClicked,
        searchText = query,
        searchState = viewState,
        onSearchChange = { viewModel.onSearchChange(it) },
        onSearchClicked = { viewModel.onSearchClicked() }
    )
}