package com.istudio.currency_result.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.istudio.common.platform.coroutines.dispatcher.MainDispatcher
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_result.presentation.states.CurrencyResultScreenResponseEvent
import com.istudio.currency_result.presentation.states.CurrencyResultScreenUiState
import com.istudio.currency_result.presentation.states.CurrencyResultScreenViewEvent
import com.istudio.models.custom.CurrencyResultInput
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyResultScreenVm @Inject constructor(
    @MainDispatcher val mainDispatcher : CoroutineDispatcher,
) : BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) { }
    // Holds the data of all the widgets in the view
    var viewState: MutableState<CurrencyResultScreenUiState> = mutableStateOf(CurrencyResultScreenUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<CurrencyResultScreenResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: CurrencyResultScreenViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CurrencyResultScreenViewEvent.SetResultDataInVm -> {
                    // ---> Card1
                    val cardOneDisplay = prepareCurrencyInputResultCard(event.data)
                    // ---> Card2
                    val cardTwoDisplay = prepareCurrencyCalculatedResultCard(event.data)
                    // Set the result in the view state
                    viewState.value = viewState.value.copy(
                        inputData = event.data,
                        userEnteredCardDetails = cardOneDisplay,
                        userCalculatedCardDetails = cardTwoDisplay
                    )
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/
    private fun calculateCurrencyConversion(input: CurrencyResultInput): String {
        val userFromEnteredCurrency = input.userFromEnteredCurrency
        val currencyToRateValue = input.currencyToRateValue
        return if(userFromEnteredCurrency!=null && currencyToRateValue!=null) {
            ((userFromEnteredCurrency.toDouble()) * (currencyToRateValue)).toString()
        }else{
            ""
        }
    }

    private fun prepareCurrencyInputResultCard(input: CurrencyResultInput): String {
        // <------------------------------------ CARD-1------------------------------------>
        // User entered currency value
        val userEnteredInputValue : String = input.userFromEnteredCurrency
        // User entered currency type
        val userEnteredInputType : String = input.userFromEnteredCurrencyName?:""
        // <------------------------------------ CARD-1------------------------------------>
        // <----------------------------- CALCULATED VALUES-------------------------------->
        val userEnteredInputResult = userEnteredInputValue.plus("--").plus(userEnteredInputType)
        // <----------------------------- CALCULATED VALUES-------------------------------->
        return userEnteredInputResult
    }

    private fun prepareCurrencyCalculatedResultCard(input: CurrencyResultInput): String {
        // <------------------------------------ CARD-2------------------------------------>
        // User calculated currency value
        val userCalculatedCurrencyValue : String = calculateCurrencyConversion(input)
        // User calculated currency type
        val userCalculatedCurrencyType: String = input.currencyToRateKey
        // <------------------------------------ CARD-2------------------------------------>
        // <----------------------------- CALCULATED VALUES-------------------------------->
        val calculatedCurrencyResult = userCalculatedCurrencyValue.plus("--").plus(userCalculatedCurrencyType)
        // <----------------------------- CALCULATED VALUES-------------------------------->
        return calculatedCurrencyResult
    }


}