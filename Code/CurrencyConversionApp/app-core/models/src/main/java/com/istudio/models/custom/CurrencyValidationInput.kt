package com.istudio.models.custom

import com.istudio.models.local.RatesEntity

data class CurrencyValidationInput(
    val userEnteredCurrencyValueInput : String,
    val userEnteredCurrencyTypeInput : String,
    val userSelectedCurrencyConversionTypeInput : List<RatesEntity>
)
