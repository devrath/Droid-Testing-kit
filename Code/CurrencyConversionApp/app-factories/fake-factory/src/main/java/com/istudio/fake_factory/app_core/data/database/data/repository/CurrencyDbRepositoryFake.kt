package com.istudio.fake_factory.app_core.data.database.data.repository

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.fake_factory.app_core.data.database.domain.CurrencyDbFeaturesFake
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbRepositoryFake @Inject constructor(
    private val features : CurrencyDbFeaturesFake
) {

    suspend fun getCurrencies(): Flow<List<CurrencyEntity>> {
        return features.getCurrencyList()
    }

    suspend fun addCurrency(currency: CurrencyEntity) {
        features.addCurrency(currency)
    }


    suspend fun addRates(currency: RatesEntity) {
        features.addRates(currency)
    }


    suspend fun getCurrencyAndRates(): List<CurrencyAndRates> {
        return features.getCurrencyAndRates()
    }

}