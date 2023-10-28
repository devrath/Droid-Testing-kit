package com.istudio.code.presentation.states

sealed class CurrencyScreenResponseEvent {
    object CurrencyUserInputError : CurrencyScreenResponseEvent()
    data class ShowSnackBar(val message: String) : CurrencyScreenResponseEvent()

}
