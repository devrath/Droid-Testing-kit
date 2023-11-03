package com.istudio.currency_result.presentation

import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.currency_result.presentation.landscape.CurrencyResultLandscape
import com.istudio.currency_result.presentation.portrait.CurrencyResultPortrait
import com.istudio.currency_result.presentation.states.CurrencyResultScreenViewEvent
import com.istudio.models.custom.CurrencyResultInput

@Composable
fun CurrencyResultScreen(
    input: CurrencyResultInput,
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
        // Set the data in view model
        viewModel.onEvent(
            CurrencyResultScreenViewEvent.SetResultDataInVm(input)
        )
        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->

        }
        // <***********> Event is observed from View-Model <************>
    }


    viewModel.viewState.value.inputData?.let {
        CurrentScreen(it,orientation)
    }

}

@Composable
private fun CurrentScreen(
    viewState: CurrencyResultInput,
    orientation: Int
) {

    // Toggle Orientation of the screen
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        CurrencyResultPortrait(viewState)
    }else{
        CurrencyResultLandscape(viewState)
    }

}


@Preview
@Composable
private fun ScreenPreview() {
    CurrentScreen(
        viewState = CurrencyResultInput(
            userFromEnteredCurrency = "22",
            userFromEnteredCurrencyType = "11",
            userFromEnteredCurrencyKey = "3",
            userFromEnteredCurrencyName = "9",
            currencyToRateKey = "82",
            currencyToRateValue = 99.2
        ),
        orientation = Configuration.ORIENTATION_PORTRAIT
    )
}