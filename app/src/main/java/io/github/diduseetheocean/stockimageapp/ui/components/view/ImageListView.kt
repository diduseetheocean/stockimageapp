package io.github.diduseetheocean.stockimageapp.ui.components.view

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.GridCells
import androidx.compose.foundation.lazy.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.ext.isPortrait
import io.github.diduseetheocean.stockimageapp.ui.components.card.ImageCard

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun ImageListView(
    images: List<ImageModel>,
    onImageClicked: (Long) -> Unit,
) {
    val currentConfig = LocalConfiguration.current
    LazyVerticalGrid(
        contentPadding = PaddingValues(top = TextFieldDefaults.MinHeight + 96.dp),
        modifier = Modifier,
        cells = GridCells.Fixed(if (currentConfig.isPortrait()) 2 else 4),
        content = {
            itemsIndexed(images) { _, item ->
                ImageCard(
                    modifier = Modifier.padding(4.dp),
                    image = item,
                    onImageClicked = { onImageClicked(it) }
                )
            }
        }
    )
}