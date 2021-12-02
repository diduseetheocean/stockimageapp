package io.github.diduseetheocean.stockimageapp.ui.components.view

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.stringResource
import io.github.diduseetheocean.stockimageapp.ext.toErrorModel

@Composable
fun ErrorView(
    throwable: Throwable,
) {
    val uiModel = throwable.toErrorModel()
    PlaceHolderView(
        icon = uiModel.errorIconId,
        message = stringResource(id = uiModel.errorMessageId),
        iconTint = MaterialTheme.colors.error
    )
}