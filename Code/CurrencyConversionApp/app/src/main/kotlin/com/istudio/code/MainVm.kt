package com.istudio.code

import android.os.Bundle
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.istudio.code.states.AppScreenResponseEvent
import com.istudio.code.states.AppScreenUiState
import com.istudio.code.states.AppScreenViewEvent
import com.istudio.common.navigation.Screen
import com.istudio.common.platform.functional.UseCaseResult
import com.istudio.common.platform.uiEvent.UiText
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_converter.domain.usecases.CommonFeaturesUseCases
import com.istudio.models.custom.CurrencyResultInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class MainVm @Inject constructor(
    private val commonFeatureUseCases : CommonFeaturesUseCases
): BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) { }

    val currentTheme = mutableStateOf(false)

    // Holds the data of all the widgets in the view
    var viewState by mutableStateOf(AppScreenUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<AppScreenResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: AppScreenViewEvent) {
        viewModelScope.launch {
            when (event) {
                is AppScreenViewEvent.CheckConnectivity -> {
                    viewState = viewState.copy(loadingState = true)
                    checkConnectivity()
                }

                is AppScreenViewEvent.LoadingState -> {
                    viewState = viewState.copy(loadingState = true)
                }

                is AppScreenViewEvent.ToolbarVisibility -> {
                    viewState = viewState.copy(isToolbarVisible = event.isVisible)
                }

                is AppScreenViewEvent.IsActionButtonVisible ->{
                    viewState = viewState.copy(isActionButtonVisible = event.isVisible)
                }

                is AppScreenViewEvent.SetToolBarTitle -> {
                    viewState = viewState.copy(toolBarTitle = event.title)
                }

                is AppScreenViewEvent.ToggleDataSource -> {
                    toggleData()
                }

                is AppScreenViewEvent.LoadFromDatabase -> {
                    // Start displaying of data from local database
                    viewState = viewState.copy(
                        loadingState = false, isConnectedToInternet = true
                    )
                }

                is AppScreenViewEvent.SetScreenOrientation -> {
                    // Main Activity Composable calls this to keep the current orientation of the screen
                    viewState = viewState.copy(orientation = event.orientation)
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** ************************************** USE CASES ******************************************/
    /**
     * USE-CASE :----> Check connectivity for the server
     */
    private fun checkConnectivity()  = uiScope.launch {
        // Invoke the use-case
        val result = commonFeatureUseCases.connectivity.invoke(Unit)
        // Collect result from use-case
        result.catch {
            displayErrorInSnackBar(UiText.DynamicString(it.message.toString()))
        }.collect{ result ->
            withContext(Dispatchers.Default) {
                // Keep the UI-state
                viewState = viewState.copy(
                    loadingState = false,
                    isConnectedToInternet = result
                )
            }
        }
    }

    /**
     * USE-CASE :----> Toggle data from DB and Server
     * If result is true then - > Indicate the UI to initiate get data from server else database
     */
    private fun toggleData() = uiScope.launch {
        try {
            val result = commonFeatureUseCases.IsNewDataToBeFetchedFromServerUseCase.invoke(Unit)
            if(result.isSuccess){
                result.map {
                    _uiEvent.send(AppScreenResponseEvent.ToggleData(it))
                }
            }else{
                displayErrorInSnackBar(UiText.DynamicString("Error in getting data from preferences"))
            }
        }catch (ex:Exception){
            displayErrorInSnackBar(UiText.DynamicString(ex.message.toString()))
        }
    }
    /** ************************************** USE CASES ******************************************/

    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun displayErrorInSnackBar(result: UiText?) {
       result?.let { _uiEvent.send(AppScreenResponseEvent.ShowSnackBar(it.toString())) }
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/


    /** ********************************* OTHER MESSAGES ****************************************/
    // Prepare route for the result screen composable
    fun prepareScreenResultRoute(resultInput: CurrencyResultInput): String {
        val userEnteredCurrencyValue = resultInput.userFromEnteredCurrency
        val userFromEnteredCurrencyType = resultInput.userFromEnteredCurrencyType
        val userFromEnteredCurrencyKey = resultInput.userFromEnteredCurrencyKey ?: ""
        val userFromEnteredCurrencyName = resultInput.userFromEnteredCurrencyName ?: ""
        val currencyToRateKey = resultInput.currencyToRateKey
        val currencyToRateValue = resultInput.currencyToRateValue.toString()

        return Screen.CurrencyResult.passParameters(
            userFromEnteredCurrency = userEnteredCurrencyValue,
            userFromEnteredCurrencyType = userFromEnteredCurrencyType,
            userFromEnteredCurrencyKey = userFromEnteredCurrencyKey,
            userFromEnteredCurrencyName = userFromEnteredCurrencyName,
            currencyToRateKey = currencyToRateKey,
            currencyToRateValue = currencyToRateValue,
        )
    }

    // Get the data from the bundle in the result screen composable
    fun getDataFromBundleForResultScreen(bundle: Bundle): CurrencyResultInput {
        val userFromEnteredCurrency =
            bundle.getString(Screen.userFromEnteredCurrency_key).toString()
        val userFromEnteredCurrencyType =
            bundle.getString(Screen.userFromEnteredCurrencyType_key).toString()
        val userFromEnteredCurrencyKey =
            bundle.getString(Screen.userFromEnteredCurrencyKey_key).toString()
        val userFromEnteredCurrencyName =
            bundle.getString(Screen.userFromEnteredCurrencyName_key).toString()
        val currencyToRateKey = bundle.getString(Screen.currencyToRateKey_key).toString()
        val currencyToRateValue = bundle.getString(Screen.currencyToRateValue_key).toString()

        return CurrencyResultInput(
            userFromEnteredCurrency = userFromEnteredCurrency,
            userFromEnteredCurrencyType = userFromEnteredCurrencyType,
            userFromEnteredCurrencyKey = userFromEnteredCurrencyKey,
            userFromEnteredCurrencyName = userFromEnteredCurrencyName,
            currencyToRateKey = currencyToRateKey,
            currencyToRateValue = currencyToRateValue.toDouble(),
        )
    }
    /** ********************************* OTHER MESSAGES ****************************************/


}