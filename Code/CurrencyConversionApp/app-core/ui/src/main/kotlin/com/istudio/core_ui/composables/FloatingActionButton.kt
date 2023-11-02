package com.istudio.core_ui.composables

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.PlayCircleFilled
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import com.istudio.currency_converter.R

@Composable
fun FloatingActionButton(
    onAction : () -> Unit
) {
    val cxt = LocalContext.current
    ExtendedFloatingActionButton(
        onClick = { onAction.invoke() },
        icon = {
            Icon(
                imageVector = Icons.Filled.PlayCircleFilled,
                contentDescription = cxt.getString(R.string.str_currency_calculate)
            )
        },
        text = { Text(text = cxt.getString(R.string.str_currency_calculate)) },
        expanded = true
    )
}