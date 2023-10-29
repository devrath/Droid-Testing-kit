package com.istudio.core_ui.composables

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.SignalWifiStatusbarConnectedNoInternet4
import androidx.compose.material3.Icon
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.istudio.core_ui.theme.LocalSpacing
import com.istudio.currency_converter.R


@Composable
fun NoConnectivity(
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    onClickAction: () -> Unit
){

    if(orientation == Configuration.ORIENTATION_PORTRAIT){
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(LocalSpacing.current.spaceExtraSmall)
        ) {
            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoConnectivityIcon()
            }
            Column(
                modifier = Modifier.weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoConnectivityActionButton(onClickAction)
            }
        }
    }else{
        Row(modifier = Modifier.fillMaxSize()
            .padding(LocalSpacing.current.spaceExtraSmall)
        ) {
            Column(
                modifier = Modifier
                    .weight(1f).fillMaxSize(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoConnectivityIcon()
            }
            Column(
                modifier = Modifier
                    .weight(1f)
                    .fillMaxHeight(),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                NoConnectivityActionButton(onClickAction)
            }
        }
    }

}

@Composable
private fun NoConnectivityActionButton(onClickAction: () -> Unit) {
    val cxt = LocalContext.current
    val strRetry = cxt.getString(R.string.str_retry)
    InputActionButton(
        modifier = Modifier.size(100.dp),
        text = strRetry,
        fontSize = 20.sp
    ) { onClickAction.invoke() }
}

@Composable
private fun NoConnectivityIcon() {
    val cxt = LocalContext.current
    val strConnectionMsg = cxt.getString(R.string.str_no_connectivity)
    Icon(
        imageVector = Icons.Default.SignalWifiStatusbarConnectedNoInternet4,
        contentDescription = strConnectionMsg,
        modifier = Modifier.size(100.dp)
    )
}

@Composable
@Preview
fun CurrentPortraitMode() {
    NoConnectivity(
        orientation = Configuration.ORIENTATION_PORTRAIT
    ){

    }
}

@Composable
@Preview(
    device = "spec:width=411dp,height=891dp,dpi=420,isRound=false,chinSize=0dp,orientation=landscape"
)
fun CurrentLandscapeMode() {
    NoConnectivity(
        orientation = Configuration.ORIENTATION_LANDSCAPE
    ){

    }
}