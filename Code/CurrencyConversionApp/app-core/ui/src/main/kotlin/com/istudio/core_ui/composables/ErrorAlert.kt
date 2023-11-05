package com.istudio.core_ui.composables

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.istudio.core_ui.theme.fontFamily
import com.istudio.currency_converter.R

@Composable
fun ErrorAlert(
    modifier : Modifier = Modifier,
    message : String,
    currentExitAlertVisibility : MutableState<Boolean>,
    closeApplication: (Boolean) -> Unit
){

    // Context
    val cxt = LocalContext.current

    if(currentExitAlertVisibility.value){
        Dialog(onDismissRequest = { true }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = MaterialTheme.colorScheme.surface
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(0.dp)) {

                        Column(
                            modifier = Modifier.fillMaxWidth().padding(30.dp),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Default.Notifications,
                                contentDescription = message,
                                modifier = Modifier.size(70.dp)
                            )
                            Spacer(modifier = Modifier.height(20.dp))
                            Text(
                                text = message,
                                style = TextStyle(
                                    fontSize = MaterialTheme.typography.titleMedium.fontSize,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Medium,
                                    fontStyle = FontStyle.Normal,
                                ),
                                textAlign = TextAlign.Center
                            )
                        }

                        Box(
                            modifier = Modifier
                                .background(color = MaterialTheme.colorScheme.tertiary)
                        ) {
                            Button(
                                onClick = { closeApplication.invoke(true) },
                                colors = ButtonDefaults.buttonColors(
                                    containerColor = MaterialTheme.colorScheme.tertiary,
                                    contentColor = MaterialTheme.colorScheme.surface
                                ),
                                shape = RoundedCornerShape(0.dp,0.dp,16.dp,16.dp),
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            ) {
                                Text(
                                    text = cxt.getString(R.string.str_ok),
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Bold,
                                    fontStyle = FontStyle.Normal,
                                )
                            }
                        }
                    }
                }
            }
        }
    }

}