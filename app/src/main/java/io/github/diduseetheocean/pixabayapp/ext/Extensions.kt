package io.github.diduseetheocean.pixabayapp.ext

import android.content.res.Configuration
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.outlined.WifiOff
import io.github.diduseetheocean.pixabayapp.R
import io.github.diduseetheocean.pixabayapp.data.model.ErrorModel
import java.net.UnknownHostException

fun Configuration.isPortrait(): Boolean = orientation == Configuration.ORIENTATION_PORTRAIT

fun Configuration.isLandScape(): Boolean = orientation == Configuration.ORIENTATION_LANDSCAPE

fun Throwable.toErrorModel(): ErrorModel = when (this) {
    is UnknownHostException -> ErrorModel(
        errorMessageId = R.string.network_error_message,
        errorIconId = Icons.Outlined.WifiOff
    )
    else -> ErrorModel()
}