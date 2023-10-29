package com.istudio.currency_converter.domain.features

import com.istudio.core.models.remote.Currencies
import com.istudio.core.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow

interface CurrencyApiFeatures {
    suspend fun getCurrencies(): Flow<Currencies>
    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues>
}