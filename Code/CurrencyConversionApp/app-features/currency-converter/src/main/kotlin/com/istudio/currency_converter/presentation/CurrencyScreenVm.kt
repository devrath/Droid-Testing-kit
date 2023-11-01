package com.istudio.currency_converter.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.istudio.common.platform.coroutines.dispatcher.MainDispatcher
import com.istudio.common.platform.uiEvent.UiText
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase.Companion.keyIsCurrienciesDisplayed
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase.Companion.keyIsCurriencyRatesDisplayed
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import com.istudio.currency_converter.presentation.states.CurrencyScreenUiState
import com.istudio.currency_converter.presentation.states.CurrencyScreenViewEvent
import com.istudio.models.custom.MasterApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class CurrencyScreenVm @Inject constructor(
    @MainDispatcher val mainDispatcher : CoroutineDispatcher,
    private val useCases : FeatureUseCases
): BaseViewModel<Unit>() {

    init {
        getDataFromServer()
    }
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

                is CurrencyScreenViewEvent.GetCurrencyDataFromDb -> {
                    // Get data from currency list from database
                    getCurrencyListFromDatabase()
                }

                is CurrencyScreenViewEvent.GetCurrencyRatesDataFromDb -> {
                    // Get data from currency rates list from database
                    getCurrencyRatesListFromDatabase()
                }

                is CurrencyScreenViewEvent.ShouldUiBeDisplayed -> {
                    // Here we set the display state visible/invisible so UI observes it and changes it
                    viewState.value = viewState.value.copy(
                        canUiBeDisplayed = event.shouldUiBeDisplayed
                    )
                }

                is CurrencyScreenViewEvent.SaveTimeStamp -> {
                    savePreferencesLocalCacheSaved(event.data)
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/
    /**
     * USE-CASE :----> Preferences saved indicating cache is saved
     */
    private fun savePreferencesLocalCacheSaved(data: MasterApiData) = uiScope.launch {
        try {
            val result = useCases.saveTimeStampUseCase.invoke(data)
            if(result.isSuccess){
                _uiEvent.send(CurrencyScreenResponseEvent.PreferencesSavedForLocalCache)
            }else{
                useCaseErrorMessage(UiText.DynamicString("Error in saving preferences data"))
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

    /**
     * USE-CASE :----> Can UI be displayed
     */
    private fun canUiBeDisplayed() = uiScope.launch {
        try{

            val input = HashMap<String,Boolean>().apply {
                this[keyIsCurrienciesDisplayed] = viewState.value.isCurrencyDataDisplayed
                this[keyIsCurriencyRatesDisplayed] = viewState.value.isCurrencyRatesDataDisplayed
            }

            val result = useCases.canUiBeDisplayedUseCase.invoke(input)

            withContext(mainDispatcher){
                if(result.isSuccess){
                    result.map { data ->
                        _uiEvent.send(
                            CurrencyScreenResponseEvent.ShouldUiBeDisplayed(data)
                        )
                    }
                }else{
                    useCaseErrorMessage(UiText.DynamicString("Retrieving data from server has failed"))
                }
            }
        }catch (ex:Exception){ errorPerformingUseCase(ex) }
    }

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
            val result = useCases.dbInsertAllData.invoke(data)
            withContext(mainDispatcher){
                if(result.isSuccess){
                    _uiEvent.send(
                        CurrencyScreenResponseEvent.InsertingCurrienciesToDbSuccessful(data = data)
                    )
                }else{
                    useCaseErrorMessage(UiText.DynamicString("Inserting to data base has failed"))
                }
            }
        }catch (ex:Exception){ errorPerformingUseCase(ex) }
    }

    private fun getCurrencyListFromDatabase()  = uiScope.launch {
        try{
            val result = useCases.dbRetrieveCurrencies.invoke(Unit)
            if(result.isSuccess){
                result.map { curriencyDataFlow ->
                    curriencyDataFlow.catch {
                        useCaseErrorMessage(UiText.DynamicString(it.message.toString()))
                    }.collect{
                        // Retrieving the currency list from DB is successful
                        viewState.value = viewState.value.copy(currencyList = it)
                        viewState.value = viewState.value.copy(isCurrencyDataDisplayed = true)
                        // Check can UI be displayed
                        canUiBeDisplayed()
                    }
                }
            }else{
                withContext(mainDispatcher){
                    useCaseErrorMessage(UiText.DynamicString("Retrieving currencies from database has failed"))
                }
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

    private fun getCurrencyRatesListFromDatabase()  = uiScope.launch {
        try{
            val result = useCases.dbRetrieveCurrencyRates.invoke(Unit)
            if(result.isSuccess){
                result.map { curriencyDataFlow ->
                    curriencyDataFlow.catch {
                        useCaseErrorMessage(UiText.DynamicString(it.message.toString()))
                    }.collect{
                        // Retrieving the currency list from DB is successful
                        viewState.value = viewState.value.copy(currencyRatesList = it)
                        viewState.value = viewState.value.copy(isCurrencyRatesDataDisplayed = true)
                        // Check can UI be displayed
                        canUiBeDisplayed()
                    }
                }
            }else{
                withContext(mainDispatcher){
                    useCaseErrorMessage(UiText.DynamicString("Retrieving currencies from database has failed"))
                }
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
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
    /** ********************************* DISPLAY MESSAGES ****************************************/


    /** ********************************* OTHER FUNCTIONS *****************************************/
    private suspend fun errorPerformingUseCase(ex: Exception) {
        withContext(mainDispatcher) {
            useCaseErrorMessage(UiText.DynamicString(ex.message.toString()))
        }
    }
    /** ********************************* OTHER FUNCTIONS *****************************************/


}