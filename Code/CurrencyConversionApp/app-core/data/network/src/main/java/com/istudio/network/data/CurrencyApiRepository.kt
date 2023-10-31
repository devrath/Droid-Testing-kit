package com.istudio.network.data

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import javax.inject.Inject

class CurrencyApiRepository @Inject constructor(
    private val features : CurrencyApiImpl
) {

    suspend fun getCurrencies(): Currencies {
        return features.getCurrencies()
    }

    suspend fun getCurrencyConversionValues(): CurrencyConversionValues {
        return features.getCurrencyConversionValues()
    }

}