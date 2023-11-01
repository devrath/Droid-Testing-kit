package com.istudio.database.domain

import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    suspend fun getCurrencyList() : Flow<List<CurrencyEntity>>
    suspend fun addCurrency(currency: CurrencyEntity)
    suspend fun addRates(rate: RatesEntity)
}