package io.github.diduseetheocean.pixabayapp.ui.components.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.pixabayapp.data.model.SearchState
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Empty
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Success
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Loading
import io.github.diduseetheocean.pixabayapp.data.model.SearchState.Error
import io.github.diduseetheocean.pixabayapp.ui.components.view.EmptyView
import io.github.diduseetheocean.pixabayapp.ui.components.view.ErrorView
import io.github.diduseetheocean.pixabayapp.ui.components.view.ImageListView
import io.github.diduseetheocean.pixabayapp.ui.components.dialog.ConfirmationDialog

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchViewContent(
    searchText: String = "",
    onSearchChange: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
    searchState: SearchState = Empty,
    onImageClicked: (Long) -> Unit = {},
) {
    var showDialog by remember { mutableStateOf(false) }
    var imageId by remember { mutableStateOf(-1L) }

    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .padding(start = 8.dp, end = 8.dp),
        contentAlignment = Alignment.TopCenter
    ) {
        when (searchState) {
            is Success -> ImageListView(searchState.images) {
                imageId = it
                showDialog = true
            }
            is Empty -> EmptyView(modifier = Modifier.align(Alignment.Center))
            is Error -> ErrorView(searchState.throwable)
        }
        Searchbar(
            searchText = searchText,
            onSearchChange = onSearchChange,
            onSearchClicked = onSearchClicked,
            isLoading = searchState is Loading,
        )
    }

    ConfirmationDialog(showDialog = showDialog, onConfirm = {
        showDialog = false
        onImageClicked(imageId)
    }) {
        showDialog = false
    }
}