package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.MasterApiData

sealed class CurrencyScreenResponseEvent {
    object CurrencyUserInputError : CurrencyScreenResponseEvent()
    data class ShowSnackBar(val message: String) : CurrencyScreenResponseEvent()
    object InsertingCurrienciesToDbSuccessful : CurrencyScreenResponseEvent()
    data class GettingDataFromServerSuccessful(val data : MasterApiData) : CurrencyScreenResponseEvent()

}
