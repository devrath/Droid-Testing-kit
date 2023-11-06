package com.istudio.currency_converter.fakes.data.repository

import com.istudio.currency_converter.fakes.domain.features.CurrencyControllerFeaturesFake
import com.istudio.currency_converter.fakes.domain.features.CurrencyDbFeaturesFake
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.mock_factory.generators.FakeCurrencyAndRates
import com.istudio.mock_factory.generators.FakeCurriencies
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class CurrencyDbRepositoryFake(
    private val features : CurrencyDbFeaturesFake
) : CurrencyControllerFeaturesFake() {

    suspend fun getCurrencyList(): Flow<List<CurrencyEntity>>{
        return features.getCurrencyList()
    }

    suspend fun addCurrency(currency: CurrencyEntity){
        return features.addCurrency(currency)
    }

    suspend fun addRates(rate: RatesEntity) {
        return features.addRates(rate)
    }
    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates>  {
        return features.getCurrencyAndRates()
    }

}