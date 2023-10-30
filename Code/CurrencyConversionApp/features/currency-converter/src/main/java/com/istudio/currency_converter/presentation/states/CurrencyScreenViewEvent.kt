package com.istudio.currency_converter.presentation.states

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val currencyInputValue: String) : CurrencyScreenViewEvent()
    object GetCurrenciesFromApi : CurrencyScreenViewEvent()
}
