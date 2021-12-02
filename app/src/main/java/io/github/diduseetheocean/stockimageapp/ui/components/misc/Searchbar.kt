package io.github.diduseetheocean.stockimageapp.ui.components.misc

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.stockimageapp.ui.components.icons.SearchbarIcon

@Composable
fun Searchbar(
    modifier: Modifier = Modifier,
    searchText: String = "",
    onSearchChange: (String) -> Unit = {},
    onSearchClicked: () -> Unit = {},
    isLoading: Boolean,
) {
    Card(
        modifier = modifier
            .padding(top = 16.dp, bottom = 8.dp, start = 4.dp, end = 4.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        TextField(
            modifier = modifier
                .fillMaxWidth(),
            value = searchText,
            colors = TextFieldDefaults.textFieldColors(
                backgroundColor = MaterialTheme.colors.surface,
                cursorColor = MaterialTheme.colors.onSurface,
                disabledLabelColor = MaterialTheme.colors.surface,
                focusedIndicatorColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent
            ),
            onValueChange = {
                onSearchChange(it)
            },
            shape = RoundedCornerShape(8.dp),
            singleLine = true,
            trailingIcon = { SearchbarIcon(isLoading, onSearchClicked) },
            keyboardActions = KeyboardActions(onDone = { onSearchClicked() })
        )
    }
}