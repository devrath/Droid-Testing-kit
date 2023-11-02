package com.istudio.core_ui.composables

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.core.text.isDigitsOnly
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.currency_converter.R

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    currencyInputText: String,
    currencyInputChange : (String) -> Unit
){
    // Context
    val cxt = LocalContext.current
    // Text strings to be displayed in composable
    val placeholder = cxt.getString(R.string.str_enter_the_amt_to_convert)
    val requiredField = cxt.getString(R.string.str_required_field)
    val maxLines = 1

    OutlinedTextField(
        modifier = modifier.padding(LocalSpacing.current.spaceExtraSmall),
        // Setting current value - Which is displayed
        value = currencyInputText,
        // Updating new value to the displayed text
        onValueChange = { updatedText ->
            if(updatedText.isDigitsOnly()){
                currencyInputChange.invoke(updatedText)
            }
        },
        // Always use copy to modify a particular attribute
        textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Left),
        placeholder = { Text(text = placeholder) },
        maxLines = maxLines,
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number),
    )
}


@Preview
@Composable
private fun CurrentDisplay() {
    InputTextField(
        currencyInputText = "100",
        currencyInputChange = { }
    )
}

