package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.database.data.repository.CurrencyDbRepository
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.data.CurrencyApiRepository
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class RepositoryControllerImpl @Inject constructor(
    // Network access
    private val api : CurrencyApiRepository,
    // Database access
    private val db : CurrencyDbRepository
) : CurrencyControllerFeatures {

    // Get the currencies fata from api
    override suspend fun getCurrenciesFromApi(): Currencies {
        return api.getCurrencies()
    }

    // Get the currency conversion values fata from api
    override suspend fun getCurrencyConversionValuesFromApi(base:String): CurrencyConversionValues {
        return api.getCurrencyConversionValues(base)
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

    override suspend fun getRatesListFromDb(): Flow<List<RatesEntity>> {
        return db.getCurrencyRates()
    }


}