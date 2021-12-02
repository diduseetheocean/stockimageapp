package io.github.diduseetheocean.stockimageapp.ui.components.icons

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextOverflow
import io.github.diduseetheocean.stockimageapp.ui.theme.Gray

@Composable
fun DetailIcon(icon: ImageVector? = null, text: String) {
    Column(
        modifier = Modifier.wrapContentSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        icon?.let {
            Icon(
                imageVector = it,
                contentDescription = "",
                tint = Gray
            )
        }
        Text(
            modifier = Modifier.wrapContentSize(),
            text = text,
            overflow = TextOverflow.Ellipsis,
            style = MaterialTheme.typography.caption.copy(color = Gray),
        )
    }
}