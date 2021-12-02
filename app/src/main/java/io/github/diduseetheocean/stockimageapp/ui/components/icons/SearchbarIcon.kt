package io.github.diduseetheocean.stockimageapp.ui.components.icons

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Search
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun SearchbarIcon(isLoading: Boolean, onSearchClicked: () -> Unit) {
    if (isLoading) {
        CircularProgressIndicator(
            modifier = Modifier.size(20.dp),
            strokeWidth = 2.dp
        )
    } else {
        IconButton(onClick = { onSearchClicked() }) {
            Icon(
                imageVector = Icons.Outlined.Search,
                tint = MaterialTheme.colors.onSurface,
                contentDescription = ""
            )
        }
    }
}