package io.github.diduseetheocean.pixabayapp.ui.components.misc

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Comment
import androidx.compose.material.icons.outlined.Download
import androidx.compose.material.icons.outlined.ThumbUp
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.pixabayapp.data.model.ImageModel
import io.github.diduseetheocean.pixabayapp.ui.components.icons.DetailIcon
import io.github.diduseetheocean.pixabayapp.ui.components.icons.ProfileDetailImage
import io.github.diduseetheocean.pixabayapp.ui.components.icons.TextTag

@Composable
fun ImageDetails(
    modifier: Modifier = Modifier,
    image: ImageModel,
    showMiscDetailIcons: Boolean = false,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(horizontal = 8.dp),
        contentAlignment = Alignment.BottomCenter
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.BottomStart)
                .padding(bottom = 4.dp),
            horizontalAlignment = Alignment.Start,
            verticalArrangement = Arrangement.Bottom
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                ProfileDetailImage(
                    url = if (showMiscDetailIcons) image.userImageURL else "",
                    text = image.userName
                )
                MiscDetailIcons(showMiscDetailIcons, image)
            }
            TagList(image.tags)
        }
    }
}

@Composable
private fun TagList(tags: List<String>) {
    LazyRow(
        modifier = Modifier
            .wrapContentSize()
            .padding(top = 4.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp),
        content = {
            items(tags) { TextTag(tagName = it) }
        }
    )
}

@Composable
private fun MiscDetailIcons(
    showMiscDetailIcons: Boolean,
    image: ImageModel,
) {
    if (showMiscDetailIcons) {
        DetailIcon(icon = Icons.Outlined.ThumbUp, image.likes)
        DetailIcon(icon = Icons.Outlined.Download, image.downloads)
        DetailIcon(icon = Icons.Outlined.Comment, image.comments)
    }
}