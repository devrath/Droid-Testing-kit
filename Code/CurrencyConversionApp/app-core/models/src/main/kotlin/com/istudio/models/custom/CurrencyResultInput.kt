package com.istudio.models.custom

data class CurrencyResultInput(
    val userFromEnteredCurrency: String,
    val userFromEnteredCurrencyType: String,
    val userFromEnteredCurrencyKey: String?,
    val userFromEnteredCurrencyName: String?,
    val currencyToRateKey: String,
    val currencyToRateValue: Double
)
