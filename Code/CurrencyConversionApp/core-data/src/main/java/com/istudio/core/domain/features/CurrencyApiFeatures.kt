package com.istudio.core.domain.features

import com.istudio.core.domain.models.remote.Currencies
import com.istudio.core.domain.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow

interface CurrencyApiFeatures {
    suspend fun getCurrencies(): Flow<Currencies>
    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues>
}