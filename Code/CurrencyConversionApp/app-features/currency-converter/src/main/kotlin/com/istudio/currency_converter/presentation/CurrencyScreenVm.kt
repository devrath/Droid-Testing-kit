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
import com.istudio.models.custom.CurrencyResultInput
import com.istudio.models.custom.CurrencyValidationInput
import com.istudio.models.custom.GridSelectionInput
import com.istudio.models.custom.MasterApiData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
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
        toggleData()
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
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyValueInput = event.currencyInputValue
                    )
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyValueInputError = false
                    )
                }

                is CurrencyScreenViewEvent.SetCurrencyTypeSelectedFromDropDown -> {
                    viewState.value = viewState.value.copy(
                        selectedDropDownModel = event.item
                    )
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyTypeInput = event.item.currencyKey
                    )
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyTypeInputError = false
                    )
                }

                is CurrencyScreenViewEvent.GetCurrenciesFromApi -> {
                    if(event.shouldNewDataBeFetchedFromServer){
                        // Getting the data from the server
                        getDataFromServer()
                    }else{
                        // Get data from currency list from database
                        getCurrencyListFromDatabase()
                        // Get data from currency rates list from database
                        getCurrencyRatesListFromDatabase()
                    }
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

                is CurrencyScreenViewEvent.SetRatesItemSelection -> {
                    setRatesItemSelected(event.position)
                }

                is CurrencyScreenViewEvent.CurrencyInputValueValidationInitiate -> {
                    // Input validation
                    initiateValidationOfCurrencyInputValue()
                }

                is CurrencyScreenViewEvent.CurrencyInputTypeValidationInitiate -> {
                    // Type validation
                    initiateValidationOfCurrencyType()
                }

                is CurrencyScreenViewEvent.ValidateCurrencyCalculation -> {
                    // All validations
                    initiateCurrencyValidation()
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    /** <*********************> Use case invocations <*******************> **/
    /**
     * USE-CASE :----> Initiate the validation of currency input value done from user
     */
    private fun initiateValidationOfCurrencyInputValue() = uiScope.launch {
        try {
            val input = viewState.value.userEnteredCurrencyValueInput
            val result = useCases.validateCurrencyInputValueUseCase.invoke(input)
            withContext(mainDispatcher){
                if(result.isSuccess){
                    _uiEvent.send(CurrencyScreenResponseEvent.CurrencyInputValueValidationSuccess)
                }else{
                    // error state
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyValueInputError = true
                    )
                    // Snack bar message
                    useCaseErrorMessage(UiText.DynamicString("Please enter a currency value to convert"))
                }
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

    /**
     * USE-CASE :----> Initiate the validation of currency type done from user
     */
    private fun initiateValidationOfCurrencyType() = uiScope.launch {
        try {
            val input = viewState.value.userEnteredCurrencyTypeInput
            val result = useCases.validateCurrencyInputValueUseCase.invoke(input)
            withContext(mainDispatcher){
                if(result.isSuccess){
                    _uiEvent.send(CurrencyScreenResponseEvent.CurrencyInputTypeValidationSuccess)
                }else{
                    // error state
                    viewState.value = viewState.value.copy(
                        userEnteredCurrencyTypeInputError = true
                    )
                    useCaseErrorMessage(
                        UiText.DynamicString("Select a currency type from drop down")
                    )
                }
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

    /**
     * USE-CASE :----> Initiate currency validation of all the selected fields
     */
    private fun initiateCurrencyValidation() = uiScope.launch {
        try{
            val input = CurrencyValidationInput(
                // Text Field - Input
                userEnteredCurrencyValueInput = viewState.value.userEnteredCurrencyValueInput,
                // Drop down menu - Input
                userEnteredCurrencyTypeInput = viewState.value.userEnteredCurrencyTypeInput,
                // Grid Selection - Input
                userSelectedCurrencyConversionTypeInput = viewState.value.currencyRatesList
            )

            val result = useCases.validateAllInputsForCalculationUseCase.invoke(input)
            withContext(mainDispatcher){
                if(result.isSuccess){

                    // Successful validation
                    val userFromEnteredCurrency = viewState.value.userEnteredCurrencyValueInput
                    val userFromEnteredCurrencyType = viewState.value.userEnteredCurrencyTypeInput
                    val userFromEnteredCurrencyKey = viewState.value.selectedDropDownModel?.currencyKey
                    val userFromEnteredCurrencyName = viewState.value.selectedDropDownModel?.currencyName

                    var currencyToRateKey = ""
                    var currencyToRateValue = 0.0

                    viewState.value.currencyRatesList.forEachIndexed { index, ratesEntity ->
                        if(ratesEntity.isItemSelected.value){
                            currencyToRateKey = ratesEntity.ratesKey
                            currencyToRateValue = ratesEntity.ratesValue!!
                        }
                    }

                    _uiEvent.send(
                        CurrencyScreenResponseEvent.DisplayResultScreen(
                            CurrencyResultInput(
                                userFromEnteredCurrency = userFromEnteredCurrency,
                                userFromEnteredCurrencyType = userFromEnteredCurrencyType,
                                userFromEnteredCurrencyKey = userFromEnteredCurrencyKey,
                                userFromEnteredCurrencyName= userFromEnteredCurrencyName,
                                currencyToRateKey = currencyToRateKey,
                                currencyToRateValue = currencyToRateValue
                            )
                        )
                    )
                }else{
                    val message = result.exceptionOrNull()?.message.toString()
                    errorPerformingUseCase(Exception(message))
                }
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

    /**
     * USE-CASE :----> Set rates list item selected
     */
    private fun setRatesItemSelected(position: Int) = uiScope.launch {
        val input = GridSelectionInput(
            data = viewState.value.currencyRatesList, selectedPosition = position
        )
        val result = useCases.setRateGridSelectionUseCase.invoke(input)
        withContext(mainDispatcher){
            if(result.isSuccess){
                result.map { result ->
                    viewState.value = viewState.value.copy(currencyRatesList = result)
                }
            }else{
                useCaseErrorMessage(UiText.DynamicString("Error during rates selection"))
            }
        }
    }

    /**
     * USE-CASE :----> Toggle data from DB and Server
     */
    private fun toggleData() = uiScope.launch {
        try {
            val result = useCases.isNewDataToBeFetchedFromServerUseCase.invoke(Unit)
            if(result.isSuccess){
                result.map {
                    _uiEvent.send(CurrencyScreenResponseEvent.ToggleData(it))
                }
            }else{
                useCaseErrorMessage(UiText.DynamicString("Error in saving preferences data"))
            }
        }catch (ex:Exception){
            errorPerformingUseCase(ex)
        }
    }

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


    /** ********************************* OTHER FUNCTIONS *****************************************/
    private suspend fun errorPerformingUseCase(ex: Exception) {
        withContext(mainDispatcher) {
            useCaseErrorMessage(UiText.DynamicString(ex.message.toString()))
        }
    }
    /** ********************************* OTHER FUNCTIONS *****************************************/


    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     * Always publish the result in the main thread
     */
    private suspend fun useCaseErrorMessage(result: UiText?) = uiScope.launch {
        result?.let {
            val message = (result as UiText.DynamicString).text
            _uiEvent.send(CurrencyScreenResponseEvent.ShowSnackBar(message))
        }
    }
    /** ********************************* DISPLAY MESSAGES ****************************************/


}