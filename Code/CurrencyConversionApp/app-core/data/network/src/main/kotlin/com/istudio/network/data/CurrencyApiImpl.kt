package com.istudio.network.data

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.api.CurrencyApi
import com.istudio.network.domain.CurrencyApiFeatures
import javax.inject.Inject

class CurrencyApiImpl @Inject constructor(
    private val api : CurrencyApi
) : CurrencyApiFeatures {
    override suspend fun getCurrencies(): Currencies {
        return api.getCurrencies()
    }

    override suspend fun getCurrencyConversionValues(base:String): CurrencyConversionValues {
        return api.getCurrencyConversionValues(base)
    }
}