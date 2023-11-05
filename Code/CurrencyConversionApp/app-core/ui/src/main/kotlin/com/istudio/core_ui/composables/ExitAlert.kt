package com.istudio.core_ui.composables

import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ExitToApp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.istudio.core_ui.theme.fontFamily
import com.istudio.currency_converter.R

@Composable
fun ExitAlert(
    modifier : Modifier = Modifier,
    currentExitAlertVisibility : MutableState<Boolean>,
    closeApplication: (Boolean) -> Unit
){

    // Context
    val cxt = LocalContext.current

    // Display the dialog if the state is true
    if (currentExitAlertVisibility.value) {
        AlertDialog(
            // On-Click of outside the alert dialog
            onDismissRequest = { true },
            icon = {
                Icon(
                    imageVector = Icons.AutoMirrored.Filled.ExitToApp,
                    contentDescription = cxt.getString(R.string.str_confirm_exit),
                    modifier = Modifier.size(70.dp)
                )
            },
            title = {
                Text(
                    text = cxt.getString(R.string.str_confirm_exit),
                    color =  MaterialTheme.colorScheme.primary,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Bold,
                    fontStyle = FontStyle.Normal,
                    fontSize = MaterialTheme.typography.titleLarge.fontSize
                )
            },
            text = {
                Text(
                    text = cxt.getString(R.string.str_exit_dialog_confirmation),
                    color =  MaterialTheme.colorScheme.primary,
                    fontFamily = fontFamily,
                    fontWeight = FontWeight.Medium,
                    fontStyle = FontStyle.Normal,
                    fontSize = MaterialTheme.typography.bodyMedium.fontSize
                )
            },
            confirmButton = {
                TextButton(
                    onClick = { closeApplication.invoke(true) }
                ) {
                    Text(
                        text = cxt.getString(R.string.str_confirm),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = fontFamily,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Normal,
                        fontStyle = FontStyle.Normal,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { closeApplication.invoke(false) }
                ) {
                    Text(
                        text = cxt.getString(R.string.str_cancel),
                        color = MaterialTheme.colorScheme.secondary,
                        fontFamily = fontFamily,
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold,
                        fontStyle = FontStyle.Normal,
                        fontSize = MaterialTheme.typography.bodyMedium.fontSize
                    )
                }
            },
            containerColor = MaterialTheme.colorScheme.background,
            textContentColor = MaterialTheme.colorScheme.background,
        )
    }

}