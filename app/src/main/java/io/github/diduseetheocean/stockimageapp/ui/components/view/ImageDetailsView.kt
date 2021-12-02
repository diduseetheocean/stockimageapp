package io.github.diduseetheocean.stockimageapp.ui.components.view

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.gestures.detectTransformGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.ExperimentalComposeUiApi
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import coil.annotation.ExperimentalCoilApi
import coil.compose.rememberImagePainter
import io.github.diduseetheocean.stockimageapp.data.model.ImageModel
import io.github.diduseetheocean.stockimageapp.data.model.preview.PreviewDataImageDetailsModel
import io.github.diduseetheocean.stockimageapp.ui.components.misc.ImageDetails

@ExperimentalCoilApi
@Preview
@Composable
fun PreviewImageDetails() {
    ImageDetailsView(PreviewDataImageDetailsModel.image)
}

@ExperimentalCoilApi
@OptIn(ExperimentalComposeUiApi::class)
@Composable
fun ImageDetailsView(image: ImageModel) {
    var scale by remember { mutableStateOf(1f) }
    Box(
        modifier = Modifier
            .background(color = Color.Transparent)
            .fillMaxSize()
            .padding(bottom = 8.dp)
            .pointerInput(Unit) {
                detectTransformGestures { _, _, zoom, _ ->
                    scale = (scale * zoom).let {
                        if (it < 1) 1.0f else it
                    }
                }
            },
    ) {
        Image(
            modifier = Modifier
                .align(Alignment.Center)
                .graphicsLayer(
                    scaleX = maxOf(.5f, minOf(3f, scale)),
                    scaleY = maxOf(.5f, minOf(3f, scale))
                ),
            painter = rememberImagePainter(image.largeImageURL),
            contentDescription = ""
        )
        ImageDetails(
            modifier = Modifier.align(Alignment.BottomCenter),
            image = image,
            showMiscDetailIcons = true
        )
    }
}