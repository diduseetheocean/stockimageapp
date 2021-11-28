package io.github.diduseetheocean.pixabayapp.ui.components.view

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import io.github.diduseetheocean.pixabayapp.data.model.SearchImagesViewModel
import io.github.diduseetheocean.pixabayapp.ui.components.misc.SearchViewContent

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