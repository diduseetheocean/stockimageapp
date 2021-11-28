package io.github.diduseetheocean.pixabayapp.ui.components.view

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import io.github.diduseetheocean.pixabayapp.R

@Composable
fun EmptyView(modifier: Modifier) {
    PlaceHolderView(
        modifier = modifier,
        icon = Icons.Outlined.Search,
        stringResource(R.string.empty_view_message)
    )
}