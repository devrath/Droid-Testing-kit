package com.istudio.currency_result.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel

@Composable
fun  CurrencyResultScreen(
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    modifier: Modifier = Modifier,
) {
    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: CurrencyResultScreenVm = hiltViewModel()
    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // View state reference from view model
    val state = viewModel.viewState
    // Composable orientation
    val configuration = LocalConfiguration.current

    LaunchedEffect(key1 = state.value.launchedEffectState) {
        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->

        }
        // <***********> Event is observed from View-Model <************>
    }


}