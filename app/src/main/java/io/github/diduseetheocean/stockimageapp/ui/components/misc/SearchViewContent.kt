package io.github.diduseetheocean.stockimageapp.ui.components.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.stockimageapp.data.model.ApiType
import io.github.diduseetheocean.stockimageapp.data.model.SearchState
import io.github.diduseetheocean.stockimageapp.data.model.SearchState.*
import io.github.diduseetheocean.stockimageapp.ui.components.dialog.ConfirmationDialog
import io.github.diduseetheocean.stockimageapp.ui.components.view.EmptyView
import io.github.diduseetheocean.stockimageapp.ui.components.view.ErrorView
import io.github.diduseetheocean.stockimageapp.ui.components.view.ImageListView

@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun SearchViewContent(
    searchText: String = "",
    onSearchChange: (String) -> Unit = {},
    onApiChange: (ApiType) -> Unit = {},
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
        DropdownItem(
            onApiChange = onApiChange
        )
    }

    ConfirmationDialog(showDialog = showDialog, onConfirm = {
        showDialog = false
        onImageClicked(imageId)
    }) {
        showDialog = false
    }
}