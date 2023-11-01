package com.istudio.currency_converter.domain.features

import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues

interface CurrencyControllerFeatures {

    // Get the currencies fata from api
    suspend fun getCurrencies() : Currencies
    // Get the currency conversion values fata from api
    suspend fun getCurrencyConversionValues()  : CurrencyConversionValues


    // Insert currency value into the Currency table
    suspend fun insertCurrencies(currency : CurrencyEntity)
    suspend fun insertRates(rates : RatesEntity)

}