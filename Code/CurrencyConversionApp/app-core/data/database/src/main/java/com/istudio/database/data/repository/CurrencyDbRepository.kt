package com.istudio.database.data.repository

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbRepository @Inject constructor(
    private val features : CurrencyDbFeatures
) {

    suspend fun getCurrencies(): Flow<List<CurrencyEntity>> {
        return features.getCurrencyList()
    }

    suspend fun getCurrencyRates(): Flow<List<RatesEntity>> {
        return features.getCurrencyRatesList()
    }

    suspend fun addCurrency(currency: CurrencyEntity) {
        features.addCurrency(currency)
    }

    suspend fun addRates(currency: RatesEntity) {
        features.addRates(currency)
    }

}