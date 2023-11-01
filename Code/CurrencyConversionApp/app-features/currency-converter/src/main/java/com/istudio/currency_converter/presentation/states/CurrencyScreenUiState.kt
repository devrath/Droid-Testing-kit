package com.istudio.currency_converter.presentation.states

import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity

data class CurrencyScreenUiState(
    val currencyUserEnteredInput: String = "",
    val currencyUserConversionInput: String = "",

    val currencyUserEnteredInputError: Boolean = false,
    val currencyUserConversionInputError: Boolean = false,

    val launchedEffectState: Boolean = false,

    val isCurrencyDataDisplayed : Boolean = false,
    val isCurrencyRatesDataDisplayed : Boolean = false,


    val currencyList: List<CurrencyEntity> = emptyList(),
    val currencyRatesList: List<RatesEntity> = emptyList(),
)
