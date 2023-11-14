package com.istudio.fake_factory.app_core.data.network.data

import com.istudio.fake_factory.app_core.data.network.api.CurrencyApiFake
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.data.CurrencyApiImpl
import javax.inject.Inject

class CurrencyApiImplFake @Inject constructor(
    private val api : CurrencyApiFake
) : CurrencyApiImpl(api=api) {

    override suspend fun getCurrencies(): Currencies {
        return api.getCurrencies()
    }

    override suspend fun getCurrencyConversionValues(base:String): CurrencyConversionValues {
        return api.getCurrencyConversionValues(base)
    }

}