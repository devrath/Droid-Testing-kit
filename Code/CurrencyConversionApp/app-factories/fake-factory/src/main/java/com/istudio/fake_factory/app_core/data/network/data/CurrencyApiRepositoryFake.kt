package com.istudio.fake_factory.app_core.data.network.data

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import javax.inject.Inject

open class CurrencyApiRepositoryFake @Inject constructor(
    private val features : CurrencyApiImplFake
) {

    suspend fun getCurrencies(): Currencies {
        return features.getCurrencies()
    }

    suspend fun getCurrencyConversionValues(base:String): CurrencyConversionValues {
        return features.getCurrencyConversionValues(base)
    }

}