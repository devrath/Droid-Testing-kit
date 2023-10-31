package com.istudio.currency_converter.presentation.states

sealed class CurrencyScreenResponseEvent {
    object CurrencyUserInputError : CurrencyScreenResponseEvent()
    data class ShowSnackBar(val message: String) : CurrencyScreenResponseEvent()

}
