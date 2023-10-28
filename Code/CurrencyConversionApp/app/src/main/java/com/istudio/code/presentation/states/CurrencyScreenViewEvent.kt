package com.istudio.code.presentation.states

sealed class CurrencyScreenViewEvent {
    data class SetCurrencyUserEnteredInput(val title: String) : CurrencyScreenViewEvent()

}
