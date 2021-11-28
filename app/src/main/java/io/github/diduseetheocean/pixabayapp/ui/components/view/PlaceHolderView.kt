package io.github.diduseetheocean.pixabayapp.ui.components.view

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun PlaceHolderView(
    modifier: Modifier = Modifier,
    icon: ImageVector,
    message: String,
    iconTint: Color = MaterialTheme.colors.secondaryVariant,
    textColor: Color = Color.Unspecified,
) {
    Box(modifier = modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .wrapContentSize()
                .align(Alignment.Center),
            verticalArrangement = Arrangement.spacedBy(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                modifier = Modifier.size(120.dp),
                imageVector = icon,
                tint = iconTint.copy(alpha = 0.5f),
                contentDescription = ""
            )
            Text(
                text = message,
                style = MaterialTheme.typography.subtitle1,
                textAlign = TextAlign.Center,
                color = textColor.copy(alpha = 0.5f)
            )
        }
    }
}