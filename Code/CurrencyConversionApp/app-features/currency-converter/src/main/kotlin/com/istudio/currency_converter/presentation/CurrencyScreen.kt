package com.istudio.currency_converter.presentation

import android.content.res.Configuration
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.istudio.common.platform.uiEvent.UiEvent
import com.istudio.core_ui.composables.LoadingAnimation
import com.istudio.currency_converter.presentation.landscape.CurrencyScreenLandscape
import com.istudio.currency_converter.presentation.portrait.CurrencyScreenPortrait
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import com.istudio.currency_converter.presentation.states.CurrencyScreenViewEvent
import com.istudio.models.custom.CurrencyResultInput

@Composable
fun CurrencyScreen(
    orientation: Int = Configuration.ORIENTATION_PORTRAIT,
    modifier: Modifier = Modifier,
    onErrorAction: (String) -> Unit,
    onKeyBoardOutsideClick: () -> Unit,
    onBackPress: () -> Unit,
    onLoading:(Boolean) -> Unit,
    onClickOfCalculatePlay: (onClick: () -> Unit) -> Unit,
    displaySnackBar : (String) -> Unit,
    navigateToResultScreen : (CurrencyResultInput) -> Unit,
    // -----> KeyBoard Action
    keyBoardDoneAction : () -> Unit
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

    // <!----------- Parent composable click actions -------------->
    onClickOfCalculatePlay {
        // Get the data from the screen and pass to parent
        // Perform validation
        viewModel.onEvent(CurrencyScreenViewEvent.CurrencyInputValueValidationInitiate)
    }
    // <!----------- Parent composable click actions -------------->


    LaunchedEffect(key1 = state.value.launchedEffectState) {
        // Initiate loading state
        onLoading.invoke(true)
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
                    // Display snackBar error
                    displaySnackBar.invoke(event.message)
                }

                is CurrencyScreenResponseEvent.PreferencesSavedForLocalCache -> {
                    print("Preferences are saved")
                }

                is CurrencyScreenResponseEvent.ToggleData -> {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.GetCurrenciesFromApi(event.shouldNewDataBeRecievedFromServer)
                    )
                }

                is CurrencyScreenResponseEvent.CurrencyInputValueValidationSuccess -> {
                    viewModel.onEvent(CurrencyScreenViewEvent.CurrencyInputTypeValidationInitiate)
                }

                is CurrencyScreenResponseEvent.CurrencyInputTypeValidationSuccess -> {
                    viewModel.onEvent(CurrencyScreenViewEvent.ValidateCurrencyCalculation)
                }

                is CurrencyScreenResponseEvent.DisplayResultScreen -> {
                    // Navigate user to result Screen
                    navigateToResultScreen.invoke(event.data)
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
                currencyInputText = state.value.userEnteredCurrencyValueInput,
                onKeyBoardOutsideClick = onKeyBoardOutsideClick,
                isCurrencyFieldError = viewModel.viewState.value.userEnteredCurrencyValueInputError,
                isCurrencyValueDropDownError = viewModel.viewState.value.userEnteredCurrencyTypeInputError,
                currencyInputChange = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetCurrencyUserEnteredInput(it))
                },
                setRatesItemSelection = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetRatesItemSelection(position = it))
                },
                onCurrencyDropDownSelection = {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.SetCurrencyTypeSelectedFromDropDown(item = it)
                    )
                },
                currencyTypeState = state.value.currencyTypeState,
                updateDropDownState = {

                },
                keyBoardDoneAction = { keyBoardDoneAction }
            )
        } else {
            CurrencyScreenLandscape(
                curriencyList = curriencyList,
                curriencyRatesList = currencyRatesList,
                currencyInputText = state.value.userEnteredCurrencyValueInput,
                onKeyBoardOutsideClick = onKeyBoardOutsideClick,
                isCurrencyFieldError = viewModel.viewState.value.userEnteredCurrencyValueInputError,
                isCurrencyValueDropDownError = viewModel.viewState.value.userEnteredCurrencyTypeInputError,
                currencyInputChange = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetCurrencyUserEnteredInput(it))
                },
                setRatesItemSelection = {
                    viewModel.onEvent(CurrencyScreenViewEvent.SetRatesItemSelection(position = it))
                },
                onCurrencyDropDownSelection = {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.SetCurrencyTypeSelectedFromDropDown(item = it)
                    )
                },
                currencyTypeState = state.value.currencyTypeState,
                updateDropDownState = {
                    viewModel.onEvent(
                        CurrencyScreenViewEvent.UpdateCurrencyTypeState(state = it)
                    )
                },
                keyBoardDoneAction = { keyBoardDoneAction }
            )
        }
    }

}