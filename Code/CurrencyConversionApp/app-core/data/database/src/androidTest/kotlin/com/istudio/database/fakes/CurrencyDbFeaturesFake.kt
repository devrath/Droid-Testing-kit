package com.istudio.database.fakes

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.mock_factory.generators.FakeCurrencyAndRates
import com.istudio.mock_factory.generators.FakeCurriencies
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyDbFeaturesFake : CurrencyDbFeatures {
    override suspend fun getCurrencyList(): Flow<List<CurrencyEntity>> = flow {
        emit(FakeCurriencies.currencyList())
    }

    override suspend fun addCurrency(currency: CurrencyEntity) {
        // Insertion of currency will be performed for the database
    }

    override suspend fun addRates(rate: RatesEntity) {
        // Insertion of rate will be performed to the database
    }

    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates> {
        return FakeCurrencyAndRates.currencyAndRateList()
    }
}