package com.istudio.currency_converter.presentation.states

import com.istudio.models.custom.CurrencyResultInput
import com.istudio.models.custom.MasterApiData

sealed class CurrencyScreenResponseEvent {
    object CurrencyUserInputError : CurrencyScreenResponseEvent()
    data class ShowSnackBar(val message: String) : CurrencyScreenResponseEvent()
    data class InsertingCurrienciesToDbSuccessful(val data : MasterApiData) : CurrencyScreenResponseEvent()
    data class GettingDataFromServerSuccessful(val data : MasterApiData) : CurrencyScreenResponseEvent()
    data class ShouldUiBeDisplayed(val shouldUiBeDisplayed: Boolean) : CurrencyScreenResponseEvent()
    object PreferencesSavedForLocalCache : CurrencyScreenResponseEvent()
    data class ToggleData(val shouldNewDataBeRecievedFromServer: Boolean) : CurrencyScreenResponseEvent()
    object CurrencyInputValueValidationSuccess : CurrencyScreenResponseEvent()
    object CurrencyInputTypeValidationSuccess : CurrencyScreenResponseEvent()

    data class DisplayResultScreen(val data: CurrencyResultInput) : CurrencyScreenResponseEvent()

}
