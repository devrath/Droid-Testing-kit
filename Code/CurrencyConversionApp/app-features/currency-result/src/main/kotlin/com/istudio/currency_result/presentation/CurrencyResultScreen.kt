package com.istudio.currency_result.presentation

import android.annotation.SuppressLint
import android.content.res.Configuration
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.currency_result.presentation.landscape.CurrencyResultLandscape
import com.istudio.currency_result.presentation.portrait.CurrencyResultPortrait
import com.istudio.currency_result.presentation.states.CurrencyResultScreenUiState
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
    // Call the launched state
    LaunchEffect(state, viewModel, input)
    // Display Screen
    CurrentScreen(
        orientation = orientation,
        state = viewModel.viewState
    )
}

@Composable
private fun LaunchEffect(
    state: MutableState<CurrencyResultScreenUiState>,
    viewModel: CurrencyResultScreenVm,
    input: CurrencyResultInput
) {
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
}

@Composable
private fun CurrentScreen(
    orientation: Int,
    state: MutableState<CurrencyResultScreenUiState>
) {

    // Toggle Orientation of the screen
    if (orientation == Configuration.ORIENTATION_PORTRAIT) {
        CurrencyResultPortrait(state)
    }else{
        CurrencyResultLandscape(state)
    }

}


@SuppressLint("UnrememberedMutableState")
@Preview
@Composable
private fun ScreenPreview() {

    val currencyInput = CurrencyResultInput(
        userFromEnteredCurrency = "100",
        userFromEnteredCurrencyType = "Rupees",
        userFromEnteredCurrencyKey = "SomeKey",
        userFromEnteredCurrencyName = "USD",
        currencyToRateKey = "SOME-KEY",
        currencyToRateValue = 3.4
    )

    val userFromEnteredCurrency = currencyInput.userFromEnteredCurrency
    val currencyToRateValue = currencyInput.currencyToRateValue
    val userCalculatedCurrencyValue  = if(userFromEnteredCurrency!=null && currencyToRateValue!=null) {
        ((userFromEnteredCurrency.toDouble()) * (currencyToRateValue)).toString()
    }else{
        ""
    }

    val data = CurrencyResultScreenUiState(
        launchedEffectState = true,
        inputData = currencyInput,
        userEnteredCardDetails = currencyInput.userFromEnteredCurrency.plus("--")
            .plus(currencyInput.userFromEnteredCurrencyType),
        userCalculatedCardDetails = userCalculatedCurrencyValue.plus("--")
            .plus(currencyInput.currencyToRateKey)
    )

    val state = mutableStateOf(data)


    CurrentScreen(
        orientation = Configuration.ORIENTATION_PORTRAIT,
        state = state
    )
}