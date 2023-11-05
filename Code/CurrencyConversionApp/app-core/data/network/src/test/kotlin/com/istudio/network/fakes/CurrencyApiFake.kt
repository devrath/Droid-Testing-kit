package com.istudio.network.fakes

import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.api.CurrencyApi

class CurrencyApiFake : CurrencyApi {
    override suspend fun getCurrencies(): Currencies {
        return FakeApiData.getFakeApiCurriencies()
    }

    override suspend fun getCurrencyConversionValues(base: String): CurrencyConversionValues {
        return FakeApiData.getFakeApiCurriencyConversionValues()
    }

}