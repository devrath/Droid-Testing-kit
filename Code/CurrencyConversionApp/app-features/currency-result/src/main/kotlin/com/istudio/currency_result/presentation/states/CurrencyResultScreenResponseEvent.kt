package com.istudio.currency_result.presentation.states

sealed class CurrencyResultScreenResponseEvent {
    data class ShowSnackBar(val message: String) : CurrencyResultScreenResponseEvent()

}
