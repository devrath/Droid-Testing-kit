package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.MasterApiData

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val currencyInputValue: String) : CurrencyScreenViewEvent()
    data class GetCurrenciesFromApi(val shouldNewDataBeFetchedFromServer: Boolean) : CurrencyScreenViewEvent()
    data class InsertDataIntoDb(val data: MasterApiData)  : CurrencyScreenViewEvent()
    data class ShouldUiBeDisplayed(val shouldUiBeDisplayed: Boolean)  : CurrencyScreenViewEvent()
    data class SaveTimeStamp(val data: MasterApiData)  : CurrencyScreenViewEvent()
    object GetCurrencyDataFromDb : CurrencyScreenViewEvent()
    object GetCurrencyRatesDataFromDb : CurrencyScreenViewEvent()


}
