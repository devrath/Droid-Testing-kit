package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.MasterApiData

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val currencyInputValue: String) : CurrencyScreenViewEvent()
    object GetCurrenciesFromApi : CurrencyScreenViewEvent()
    data class InsertDataIntoDb(val data: MasterApiData)  : CurrencyScreenViewEvent()
}
