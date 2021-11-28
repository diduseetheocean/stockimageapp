package io.github.diduseetheocean.pixabayapp.data.model

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.Error
import androidx.compose.ui.graphics.vector.ImageVector
import io.github.diduseetheocean.pixabayapp.R

data class ErrorModel(
    val errorMessageId: Int = R.string.error_message,
    val errorIconId: ImageVector = Icons.Outlined.Error,
)