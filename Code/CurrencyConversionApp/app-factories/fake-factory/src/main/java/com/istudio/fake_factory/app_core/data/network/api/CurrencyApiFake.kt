package com.istudio.fake_factory.app_core.data.network.api

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.api.CurrencyApi

class CurrencyApiFake : CurrencyApi {
    override suspend fun getCurrencies(): Currencies {
        TODO("Not yet implemented")
    }

    override suspend fun getCurrencyConversionValues(base: String): CurrencyConversionValues {
        TODO("Not yet implemented")
    }
}