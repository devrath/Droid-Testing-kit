package com.istudio.fake_factory.app_features.currency_converter.data.implementation

import com.istudio.fake_factory.app_core.data.database.data.repository.CurrencyDbRepositoryFake
import com.istudio.fake_factory.app_core.data.network.data.CurrencyApiRepositoryFake
import com.istudio.fake_factory.app_features.currency_converter.domain.features.CurrencyControllerFeaturesFake
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow

class RepositoryControllerImplFake(
    // Network access
    private val api : CurrencyApiRepositoryFake,
    // Database access
    private val db : CurrencyDbRepositoryFake
) : CurrencyControllerFeaturesFake() {

    override suspend fun getCurrenciesFromApi(): Currencies {
        return api.getCurrencies()
    }

    override suspend fun getCurrencyConversionValuesFromApi(base:String): CurrencyConversionValues {
        return api.getCurrencyConversionValues(base)
    }

    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates> {
        return db.getCurrencyAndRates()
    }

    override suspend fun insertCurrenciesIntoDb(currency: CurrencyEntity) {
        db.addCurrency(currency)
    }

    override suspend fun insertRatesIntoDb(rates: RatesEntity) {
        db.addRates(rates)
    }

    override suspend fun getCurrenciesListFromDb(): Flow<List<CurrencyEntity>> {
        return db.getCurrencies()
    }

    override suspend fun getRatesListFromDb(): List<CurrencyAndRates> {
        return db.getCurrencyAndRates()
    }

}