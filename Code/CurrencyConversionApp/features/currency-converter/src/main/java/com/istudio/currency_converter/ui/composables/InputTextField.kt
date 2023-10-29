package com.istudio.currency_converter.ui.composables

/*import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.LocalTextStyle
import androidx.compose.material3.OutlinedTextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.material3.Text
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.core.text.isDigitsOnly

@Composable
fun InputTextField(
    modifier: Modifier = Modifier,
    state: MutableState<String>
){

    OutlinedTextField(
        // Setting current value - Which is displayed
        value = state.value,
        // Updating new value to the displayed text
        onValueChange = { updatedText ->
            if(updatedText.isDigitsOnly()){
                // Set only if they are digits
                state.value = updatedText
            }
        },
        // Always use copy to modify a particular attribute
        textStyle = LocalTextStyle.current.copy( textAlign = TextAlign.Left),
        label = { Text(text = "Enter the amount to convert") },
        placeholder = { Text(text = "Height") },
        keyboardOptions = KeyboardOptions( keyboardType = KeyboardType.Number),
        supportingText = { Text(text = "Required Field") },
    )
}*/

