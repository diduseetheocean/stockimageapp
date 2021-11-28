package io.github.diduseetheocean.pixabayapp.ui.components.dialog

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.AlertDialog
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.pixabayapp.R

@Composable
fun ConfirmationDialog(
    showDialog: Boolean,
    onConfirm: () -> Unit,
    onDismiss: () -> Unit,
) {
    if (showDialog) {
        AlertDialog(
            modifier = Modifier.fillMaxWidth(),
            title = { Text(stringResource(R.string.dialog_title_open_image_details)) },
            onDismissRequest = onDismiss,
            buttons = {
                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp),
                    horizontalArrangement = Arrangement.End,

                    ) {
                    TextButton(onClick = onDismiss) {
                        Text(text = stringResource(R.string.dialog_negative_option))
                    }
                    TextButton(onClick = onConfirm) {
                        Text(text = stringResource(R.string.dialog_positive_option))
                    }
                }
            }
        )
    }
}