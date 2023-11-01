package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.database.data.repository.CurrencyDbRepository
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.data.CurrencyApiRepository
import javax.inject.Inject

class RepositoryControllerImpl @Inject constructor(
    // Network access
    private val api : CurrencyApiRepository,
    // Database access
    private val db : CurrencyDbRepository
) : CurrencyControllerFeatures {

    // Get the currencies fata from api
    override suspend fun getCurrencies(): Currencies {
        return api.getCurrencies()
    }

    // Get the currency conversion values fata from api
    override suspend fun getCurrencyConversionValues(): CurrencyConversionValues {
        return api.getCurrencyConversionValues()
    }


}