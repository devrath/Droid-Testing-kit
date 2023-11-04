package com.istudio.database.domain

import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    suspend fun getCurrencyList() : Flow<List<CurrencyEntity>>
    suspend fun getCurrencyRatesList() : List<RatesEntity>
    suspend fun addCurrency(currency: CurrencyEntity)
    suspend fun addRates(rate: RatesEntity)
    suspend fun getCurrencyAndRates() : List<CurrencyAndRates>
}