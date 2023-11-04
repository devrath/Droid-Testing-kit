package com.istudio.core_ui.composables

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.expandVertically
import androidx.compose.animation.shrinkVertically
import androidx.compose.foundation.layout.fillMaxWidth
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
import com.istudio.core_ui.theme.fontFamily
import com.istudio.currency_converter.R
import com.istudio.models.local.CurrencyEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DropDownField(
    modifier: Modifier = Modifier,
    isError: Boolean = false,
    dataList: List<CurrencyEntity> = mutableListOf(),
    isExpanded: MutableState<Boolean> = mutableStateOf(false),
    selectionAction: (CurrencyEntity) -> Unit,
    // -----> Drop down data
    currencyTypeState: MutableState<String> = mutableStateOf(""),
    updateDropDownState: (MutableState<String>) -> Unit
) {

    // Context
    val cxt = LocalContext.current
    // Text strings to be displayed in composable
    val placeholder = cxt.getString(R.string.str_entered_currency_type)

    var isExpandedLocal by remember { isExpanded }
    var actionTextLocal by remember { currencyTypeState }

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
            modifier = Modifier.menuAnchor().fillMaxWidth(),
            placeholder = {
                Text(
                    text = placeholder,
                    fontFamily = fontFamily,
                )
            },
            isError = isError
        )

        AnimatedVisibility(
            visible = isExpandedLocal, enter = expandVertically(), exit = shrinkVertically()
        ) {
            ExposedDropdownMenu(
                modifier = Modifier.animateContentSize(),
                expanded = isExpandedLocal,
                onDismissRequest = {
                    isExpanded.value = false
                    isExpandedLocal = false
                }
            ) {
                dataList.forEachIndexed { index, item ->
                    DropdownMenuItem(
                        modifier = Modifier.exposedDropdownSize(),
                        text = {
                            Text(
                                text = item.currencyName.toString(),
                                fontFamily = fontFamily,
                            )
                        },
                        onClick = {
                            actionTextLocal = item.currencyName.toString()
                            isExpandedLocal = false
                            selectionAction.invoke(item)
                            updateDropDownState.invoke(currencyTypeState)
                        }
                    )
                }
            }
        }

    }

}


@Preview
@Composable
private fun CurrentDisplay() {
    DropDownField(
        dataList = mutableListOf(),
        updateDropDownState = {

        },
        selectionAction = {

        }
    )
}