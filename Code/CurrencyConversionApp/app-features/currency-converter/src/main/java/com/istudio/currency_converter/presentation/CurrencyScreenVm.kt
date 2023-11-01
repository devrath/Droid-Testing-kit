package com.istudio.currency_converter.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.istudio.common.platform.coroutines.dispatcher.MainDispatcher
import com.istudio.common.platform.functional.UseCaseResult
import com.istudio.common.platform.uiEvent.UiText
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_converter.R
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import com.istudio.currency_converter.presentation.states.CurrencyScreenUiState
import com.istudio.currency_converter.presentation.states.CurrencyScreenViewEvent
import com.istudio.models.custom.MasterApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyScreenVm @Inject constructor(
    @MainDispatcher val mainDispatcher : CoroutineDispatcher,
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
                    // Update the latest UI state value in the state holder
                    viewState.value = viewState.value.copy(currencyUserEnteredInput = event.currencyInputValue)
                }

                is CurrencyScreenViewEvent.GetCurrenciesFromApi -> {
                    // Getting the data from the server
                    getDataFromServer()
                }

                is CurrencyScreenViewEvent.InsertDataIntoDb -> {
                    // Inserting the data into the database
                    insertIntoDatabase(event.data)
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/
    /**
     * USE-CASE :----> Getting the data from server
     */
    private fun getDataFromServer() = uiScope.launch {
        try{
            val result = useCases.network.invoke(Unit)
            withContext(mainDispatcher){
                if(result.isSuccess){
                    result.map { data ->
                        _uiEvent.send(
                            CurrencyScreenResponseEvent.GettingDataFromServerSuccessful(data)
                        )
                    }
                }else{
                    useCaseErrorMessage(UiText.DynamicString("Retrieving data from server has failed"))
                }
            }
        }catch (ex:Exception){ errorPerformingUseCase(ex) }
    }

    /**
     * USE-CASE :----> Inserting the data into local database
     */
    private fun insertIntoDatabase(data: MasterApiData) = uiScope.launch {
        try{
            val result = useCases.database.invoke(data)
            withContext(mainDispatcher){
                if(result.isSuccess){
                    _uiEvent.send(CurrencyScreenResponseEvent.InsertingCurrienciesToDbSuccessful)
                }else{
                    useCaseErrorMessage(UiText.DynamicString("Inserting to data base has failed"))
                }
            }
        }catch (ex:Exception){ errorPerformingUseCase(ex) }
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
    /** ********************************* DISPLAY MESSAGES ****************************************/


    /** ********************************* OTHER FUNCTIONS *****************************************/
    private suspend fun errorPerformingUseCase(ex: Exception) {
        withContext(mainDispatcher) {
            useCaseErrorMessage(UiText.DynamicString(ex.message.toString()))
        }
    }
    /** ********************************* OTHER FUNCTIONS *****************************************/


}