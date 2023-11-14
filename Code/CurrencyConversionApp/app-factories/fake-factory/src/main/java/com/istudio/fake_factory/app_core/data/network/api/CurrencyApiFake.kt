package com.istudio.fake_factory.app_core.data.network.api

import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.api.CurrencyApi

class CurrencyApiFake : CurrencyApi {
    override suspend fun getCurrencies(): Currencies {
        // ---------> Here we get from JSON - Mocked data
        return FakeApiData.getFakeApiCurriencies()
    }

    override suspend fun getCurrencyConversionValues(base: String): CurrencyConversionValues {
        // ---------> Here we get from JSON - Mocked data
        return FakeApiData.getFakeApiCurriencyConversionValues()
    }
}