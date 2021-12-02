package io.github.diduseetheocean.stockimageapp.ui.components.misc

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import io.github.diduseetheocean.stockimageapp.data.model.ApiType

@Composable
fun DropdownItem(
    onApiChange: (ApiType) -> Unit = {}
) {
    var expanded by remember { mutableStateOf(false) }
    val items = ApiType.values()
    var selectedIndex by remember { mutableStateOf(0) }
    Card(
        modifier = Modifier
            .padding(top = 84.dp, bottom = 8.dp, start = 4.dp, end = 4.dp)
            .fillMaxWidth()
            .clickable(onClick = { expanded = true })
            .height(50.dp),
        elevation = 8.dp,
        shape = RoundedCornerShape(8.dp)
    ) {
        Text(
            items[selectedIndex].toString(),
            modifier = Modifier
                .padding(start = 16.dp)
                .wrapContentSize(Alignment.CenterStart)
                .background(MaterialTheme.colors.surface),
            textAlign = TextAlign.Justify
        )
        DropdownMenu(
            expanded = expanded,
            onDismissRequest = { expanded = false },
            modifier = Modifier
                .wrapContentWidth()
                .background(
                    MaterialTheme.colors.surface
                )
        ) {
            items.forEachIndexed { index, type ->
                DropdownMenuItem(onClick = {
                    selectedIndex = index
                    onApiChange(type)
                    expanded = false
                }) {
                    Text(text = type.toString())
                }
            }
        }
    }
}