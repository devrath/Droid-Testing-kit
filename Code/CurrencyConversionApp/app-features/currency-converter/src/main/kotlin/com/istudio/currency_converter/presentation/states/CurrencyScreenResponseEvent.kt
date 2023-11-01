package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.MasterApiData

sealed class CurrencyScreenResponseEvent {
    object CurrencyUserInputError : CurrencyScreenResponseEvent()
    data class ShowSnackBar(val message: String) : CurrencyScreenResponseEvent()
    data class InsertingCurrienciesToDbSuccessful(val data : MasterApiData) : CurrencyScreenResponseEvent()
    data class GettingDataFromServerSuccessful(val data : MasterApiData) : CurrencyScreenResponseEvent()

    data class ShouldUiBeDisplayed(val shouldUiBeDisplayed: Boolean) : CurrencyScreenResponseEvent()

}
