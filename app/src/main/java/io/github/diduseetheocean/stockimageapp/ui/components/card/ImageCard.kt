package io.github.diduseetheocean.stockimageapp.ui.components.card

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.ui.components.misc.ImageDetails

@OptIn(ExperimentalCoilApi::class)
@Composable
fun ImageCard(
    modifier: Modifier = Modifier,
    image: ImageModel,
    showMiscDetailIcons: Boolean = false,
    onImageClicked: (Long) -> Unit,
) {
    Card(
        modifier = modifier
            .fillMaxWidth()
            .height(200.dp)
            .clickable { onImageClicked(image.imageId) },
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Column {
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(150.dp),
                painter = rememberImagePainter(image.url),
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            ImageDetails(image = image, showMiscDetailIcons = showMiscDetailIcons)
        }
    }
}