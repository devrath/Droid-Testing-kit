package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.MasterApiData
import com.istudio.models.local.CurrencyEntity

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val currencyInputValue: String) : CurrencyScreenViewEvent()
    data class GetCurrenciesFromApi(val shouldNewDataBeFetchedFromServer: Boolean) : CurrencyScreenViewEvent()
    data class InsertDataIntoDb(val data: MasterApiData)  : CurrencyScreenViewEvent()
    data class ShouldUiBeDisplayed(val shouldUiBeDisplayed: Boolean)  : CurrencyScreenViewEvent()
    data class SaveTimeStamp(val data: MasterApiData)  : CurrencyScreenViewEvent()
    object GetCurrencyDataFromDb : CurrencyScreenViewEvent()
    object GetCurrencyRatesDataFromDb : CurrencyScreenViewEvent()
    data class SetRatesItemSelection(val position: Int)  : CurrencyScreenViewEvent()
    object ValidateCurrencyCalculation : CurrencyScreenViewEvent()
    data class SetCurrencyTypeSelectedFromDropDown(val item: CurrencyEntity) : CurrencyScreenViewEvent()
    object CurrencyInputValueValidationInitiate : CurrencyScreenViewEvent()
    object CurrencyInputTypeValidationInitiate : CurrencyScreenViewEvent()

}
