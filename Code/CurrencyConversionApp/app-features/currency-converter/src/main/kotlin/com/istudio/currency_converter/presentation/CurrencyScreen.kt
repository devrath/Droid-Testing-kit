package com.istudio.currency_converter.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.lifecycle.ViewModelStoreOwner
import androidx.lifecycle.viewmodel.compose.viewModel
import com.istudio.core_ui.composables.LoadingAnimation
import com.istudio.currency_converter.presentation.landscape.CurrencyScreenLandscape
import com.istudio.currency_converter.presentation.portrait.CurrencyScreenPortrait
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import com.istudio.currency_converter.presentation.states.CurrencyScreenViewEvent

@Composable
fun CurrencyScreen(
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    modifier: Modifier = Modifier,
    onErrorAction: (String) -> Unit,
    onKeyBoardOutsideClick: () -> Unit,
    onBackPress: () -> Unit,
    onLoading:(Boolean) -> Unit
) {

    // <!------------ MAIN-COMPOSE-CONTROL-PARTS ----------------->
    // Context
    val cxt = LocalContext.current
    // View model reference
    val viewModel: CurrencyScreenVm = hiltViewModel()
    // View state reference from view model
    val state = viewModel.viewState
    // Composable orientation
    val configuration = LocalConfiguration.current

    val curriencyList = viewModel.viewState.value.currencyList
    val currencyRatesList = viewModel.viewState.value.currencyRatesList
    // <!----------- MAIN-COMPOSE-CONTROL-PARTS ------------------->

    LaunchedEffect(key1 = state.value.launchedEffectState) {
        // <***********> Event is observed from View-Model <************>
        viewModel.uiEvent.collect { event ->
            when (event) {
                is CurrencyScreenResponseEvent.GettingDataFromServerSuccessful -> {
                    // Getting the data from the server is successful
                    viewModel.onEvent(CurrencyScreenViewEvent.InsertDataIntoDb(event.data))
                }

                is CurrencyScreenResponseEvent.InsertingCurrienciesToDbSuccessful -> {
                    // Inserting the value in the local database is successful
                    // Then : -> Fetch data from database -> Currency List
                    viewModel.onEvent(CurrencyScreenViewEvent.GetCurrencyDataFromDb)
                    // Then : -> Fetch data from database -> CurrencyRates List
                    viewModel.onEvent(CurrencyScreenViewEvent.GetCurrencyRatesDataFromDb)
                    // Then : -> Store the timestamp and flag that data local data is available
                    viewModel.onEvent(CurrencyScreenViewEvent.SaveTimeStamp(data = event.data))
                }

                is CurrencyScreenResponseEvent.ShouldUiBeDisplayed -> {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.ShouldUiBeDisplayed(event.shouldUiBeDisplayed)
                    )
                    onLoading.invoke(event.shouldUiBeDisplayed)
                }

                is CurrencyScreenResponseEvent.CurrencyUserInputError -> {

                }

                is CurrencyScreenResponseEvent.ShowSnackBar -> {

                }

                is CurrencyScreenResponseEvent.PreferencesSavedForLocalCache -> {
                    print("Preferences are saved")
                }

                is CurrencyScreenResponseEvent.ToggleData -> {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.GetCurrenciesFromApi(event.shouldNewDataBeRecievedFromServer)
                    )
                }
            }
        }
        // <***********> Event is observed from View-Model <************>
    }

    if (!viewModel.viewState.value.canUiBeDisplayed) {
        Column(
            modifier = modifier.fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            LoadingAnimation()
        }
    } else {
        // Toggle Orientation of the screen
        if (orientation == Configuration.ORIENTATION_PORTRAIT) {
            CurrencyScreenPortrait(
                curriencyList = curriencyList,
                curriencyRatesList = currencyRatesList,
                currencyInputText = state.value.currencyUserEnteredInput,
                onKeyBoardOutsideClick = onKeyBoardOutsideClick,
                currencyInputChange = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetCurrencyUserEnteredInput(it))
                }
            )
        } else {
            CurrencyScreenLandscape(
                curriencyList = curriencyList,
                curriencyRatesList = currencyRatesList,
                currencyInputText = state.value.currencyUserEnteredInput,
                onKeyBoardOutsideClick = onKeyBoardOutsideClick,
                currencyInputChange = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetCurrencyUserEnteredInput(it))
                }
            )
        }
    }

}