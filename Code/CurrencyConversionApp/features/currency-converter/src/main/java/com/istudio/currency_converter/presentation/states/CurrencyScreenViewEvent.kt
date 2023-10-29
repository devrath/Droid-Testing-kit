package com.istudio.currency_converter.presentation.states

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val title: String) : CurrencyScreenViewEvent()

}
