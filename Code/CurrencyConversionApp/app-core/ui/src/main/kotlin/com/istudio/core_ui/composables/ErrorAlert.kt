package com.istudio.core_ui.composables

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.istudio.core_ui.theme.fontFamily

@Composable
fun ErrorAlert(
    modifier : Modifier = Modifier,
    message : String,
    currentExitAlertVisibility : MutableState<Boolean>,
    closeApplication: (Boolean) -> Unit
){

    if(currentExitAlertVisibility.value){
        Dialog(onDismissRequest = { true }) {
            Surface(
                shape = RoundedCornerShape(16.dp),
                color = Color.White
            ) {
                Box(
                    contentAlignment = Alignment.Center
                ) {
                    Column(modifier = Modifier.padding(20.dp)) {

                        Column(
                            modifier = Modifier.fillMaxWidth(),
                            verticalArrangement = Arrangement.Center,
                            horizontalAlignment = Alignment.CenterHorizontally
                        ) {
                            Icon(
                                imageVector = Icons.Filled.Notifications,
                                contentDescription = "",
                                tint = colorResource(android.R.color.darker_gray),
                                modifier = Modifier
                                    .width(30.dp)
                                    .height(30.dp)
                            )
                            Text(
                                text = message,
                                style = TextStyle(
                                    fontSize = 24.sp,
                                    fontFamily = fontFamily,
                                    fontWeight = FontWeight.Bold
                                )
                            )
                        }

                        Spacer(modifier = Modifier.height(20.dp))

                        Box(modifier = Modifier.padding(40.dp, 0.dp, 40.dp, 0.dp)) {
                            Button(
                                onClick = {
                                    closeApplication.invoke(true)
                                },
                                shape = RoundedCornerShape(50.dp),
                                modifier = Modifier.fillMaxWidth().height(50.dp)
                            ) {
                                Text(text = "Done")
                            }
                        }
                    }
                }
            }
        }
    }

}