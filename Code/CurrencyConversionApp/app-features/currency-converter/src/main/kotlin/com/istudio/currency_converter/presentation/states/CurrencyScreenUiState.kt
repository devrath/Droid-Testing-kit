package com.istudio.currency_converter.presentation.states

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity

data class CurrencyScreenUiState(
    // User entered currency ---> value
    val userEnteredCurrencyValueInput: String = "",
    // User entered currency type  ---> value
    val userEnteredCurrencyTypeInput: String = "",
    // Currency conversion input ---> value
    val userSelectedCurrencyConversionTypeInput: String = "",

    val selectedDropDownModel : CurrencyEntity? = null,
    val currencyTypeState : MutableState<String> = mutableStateOf(""),

    // User entered currency ---> boolean
    val userEnteredCurrencyValueInputError: Boolean = false,
    // User entered currency type  ---> boolean
    val userEnteredCurrencyTypeInputError: Boolean = false,
    // Currency conversion input  ---> boolean
    val userSelectedCurrencyConversionTypeInputError: Boolean = false,

    // This flg is set once - > From launched effect state, To start collecting emits only once
    val launchedEffectState: Boolean = false,
    // This variable holds the currency list data
    val currencyList: List<CurrencyEntity> = emptyList(),
    // This variable holds the currency rates data
    val currencyRatesList: List<RatesEntity> = emptyList(),
    // This flag indicates if the currency information is displayed or not
    val isCurrencyDataDisplayed : Boolean = false,
    // This flag indicates if the rates information is displayed or not
    val isCurrencyRatesDataDisplayed : Boolean = false,
    // This flag controls the visibility of UI whether it has to be visible or invisible
    val canUiBeDisplayed : Boolean = false
)
