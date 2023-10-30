package com.istudio.currency_converter.domain.features

import com.istudio.core.models.remote.Currencies
import com.istudio.core.models.remote.CurrencyConversionValues

interface CurrencyApiFeatures {
    suspend fun getCurrencies(): Currencies
    suspend fun getCurrencyConversionValues(): CurrencyConversionValues
}