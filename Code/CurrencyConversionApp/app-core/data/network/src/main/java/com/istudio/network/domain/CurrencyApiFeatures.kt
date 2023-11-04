package com.istudio.network.domain

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues

interface CurrencyApiFeatures {
    suspend fun getCurrencies(): Currencies
    suspend fun getCurrencyConversionValues(base:String): CurrencyConversionValues
}