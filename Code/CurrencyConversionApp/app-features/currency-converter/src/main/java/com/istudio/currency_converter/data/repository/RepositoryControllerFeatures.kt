package com.istudio.currency_converter.data.repository

import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import javax.inject.Inject

class RepositoryControllerFeatures @Inject constructor(
    private val repoImpl : CurrencyControllerFeatures
) : CurrencyControllerFeatures {

    // Get the currencies fata from api
    override suspend fun getCurrencies(): Currencies {
        return repoImpl.getCurrencies()
    }

    // Get the currency conversion values fata from api
    override suspend fun getCurrencyConversionValues(): CurrencyConversionValues {
        return repoImpl.getCurrencyConversionValues()
    }


}