package io.github.diduseetheocean.pixabayapp.ui.components.icons

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.sp
import io.github.diduseetheocean.pixabayapp.R
import io.github.diduseetheocean.pixabayapp.ui.theme.Gray

@Composable
fun TextTag(
    tagName: String
) {
    Text(
        text = stringResource(R.string.tag_prefix) + tagName,
        color = Gray,
        fontSize = 12.sp
    )
}