package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyApiFeatures
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.core.modules.network.api.CurrencyApi
import javax.inject.Inject

class CurrencyApiImpl @Inject constructor(
    private val api : CurrencyApi
) : CurrencyApiFeatures {
    override suspend fun getCurrencies(): Currencies {
        return api.getCurrencies()
    }

    override suspend fun getCurrencyConversionValues(): CurrencyConversionValues {
        return api.getCurrencyConversionValues()
    }
}