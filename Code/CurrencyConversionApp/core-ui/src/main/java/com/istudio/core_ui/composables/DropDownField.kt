package com.istudio.core_ui.composables

import androidx.compose.foundation.layout.IntrinsicSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import com.istudio.currency_converter.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownField(
    modifier: Modifier = Modifier,
    dataList: MutableList<String> = mutableListOf(),
    isExpanded: MutableState<Boolean> = mutableStateOf(false),
    actionText: MutableState<String> = mutableStateOf("")
) {

    // Context
    val cxt = LocalContext.current
    // Text strings to be displayed in composable
    val placeholder = cxt.getString(R.string.str_entered_currency_type)

    var isExpandedLocal by remember { isExpanded }
    var actionTextLocal by remember { actionText }

    ExposedDropdownMenuBox(
        expanded = isExpandedLocal,
        onExpandedChange = {
            isExpanded.value = it
            isExpandedLocal = it
        }
    ) {
        TextField(
            value = actionTextLocal,
            onValueChange = { },
            readOnly = true,
            trailingIcon = {
                ExposedDropdownMenuDefaults.TrailingIcon(
                    expanded = isExpandedLocal
                )
            },
            colors = ExposedDropdownMenuDefaults.textFieldColors(),
            modifier = Modifier.menuAnchor(),
            placeholder = { Text(text = placeholder) }
        )

        ExposedDropdownMenu(
            expanded = isExpandedLocal,
            onDismissRequest = {
                isExpanded.value = false
                isExpandedLocal = false
            }
        ) {

            dataList.forEachIndexed { index, item ->
                DropdownMenuItem(
                    modifier = Modifier.exposedDropdownSize(),
                    text = { Text(text = item) },
                    onClick = {
                        actionTextLocal = item
                        isExpandedLocal = false
                    }
                )
            }
        }
    }

}


@Preview
@Composable
private fun CurrentDisplay() {
    DropDownField(
        dataList = mutableListOf("Item-1,Item-2,Item-3")
    )
}