package com.istudio.core_ui.composables

import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.TextUnit

@Composable
fun InputActionButton(
    modifier : Modifier = Modifier,
    fontSize : TextUnit = TextUnit.Unspecified,
    text: String,
        onClickAction: () -> Unit
) {
    Button(
        modifier = modifier,
        onClick = { onClickAction }) {
        Text(
            text = text,
            fontSize = fontSize
        )
    }
}