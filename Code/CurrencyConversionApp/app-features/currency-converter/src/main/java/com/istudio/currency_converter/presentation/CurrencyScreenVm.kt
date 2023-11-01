package com.istudio.currency_converter.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.istudio.common.platform.functional.UseCaseResult
import com.istudio.common.platform.uiEvent.UiText
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import com.istudio.currency_converter.presentation.states.CurrencyScreenUiState
import com.istudio.currency_converter.presentation.states.CurrencyScreenViewEvent
import com.istudio.models.custom.MasterApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyScreenVm @Inject constructor(
    private val useCases : FeatureUseCases
): BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) = Unit

    // Holds the data of all the widgets in the view
    var viewState: MutableState<CurrencyScreenUiState> = mutableStateOf(CurrencyScreenUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<CurrencyScreenResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()


    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: CurrencyScreenViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CurrencyScreenViewEvent.SetCurrencyUserEnteredInput -> {
                    viewState.value = viewState.value.copy(currencyUserEnteredInput = event.currencyInputValue)
                }

                is CurrencyScreenViewEvent.GetCurrenciesFromApi -> {
                   getDataFromServer()
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/
    private fun getDataFromServer() = uiScope.launch {
        try{
            val result = useCases.network.invoke(Unit)
            if(result.isSuccess){
                result.map { data ->
                    println(data)
                    insertIntoDatabase(data)
                }
            }
        }catch (ex:Exception){
            useCaseErrorMessage(UiText.DynamicString(ex.message.toString()))
        }
    }

    private fun insertIntoDatabase(data: MasterApiData) = uiScope.launch {
        try{
            val result = useCases.database.invoke(data)
            if(result.isSuccess){
                result.map { data ->
                    println(data)
                }
            }
        }catch (ex:Exception){
            useCaseErrorMessage(UiText.DynamicString(ex.message.toString()))
        }
    }
    /** <*********************> Use case invocations <*******************> **/


    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     * Always publish the result in the main thread
     */
    private suspend fun useCaseErrorMessage(result: UiText?) = uiScope.launch {
        result?.let { _uiEvent.send(CurrencyScreenResponseEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        _uiEvent.send(CurrencyScreenResponseEvent.ShowSnackBar(uiEvent.text))
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/



}