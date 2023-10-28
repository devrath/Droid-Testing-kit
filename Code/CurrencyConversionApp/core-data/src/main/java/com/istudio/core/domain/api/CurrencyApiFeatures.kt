package com.istudio.core.domain.api

import com.istudio.core.domain.models.Currencies
import com.istudio.core.domain.models.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow

interface CurrencyApiFeatures {
    suspend fun getCurrencies(): Flow<Currencies>
    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues>
}