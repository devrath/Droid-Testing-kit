package com.istudio.mock_factory.generators

import com.google.gson.Gson
import com.istudio.mock_factory.generators.FakeApiData.ServerResources.CURRENCIES_FILE
import com.istudio.mock_factory.generators.FakeApiData.ServerResources.CURRENCY_CONVERSION_VALUES_FILE
import com.istudio.models.custom.MasterApiData
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.utils.FileUtil

object FakeApiData {

    object ServerResources {
        const val CURRENCIES_FILE = "Currencies.json"
        const val CURRENCY_CONVERSION_VALUES_FILE = "CurrencyConversionValues.json"
    }


    fun getMasterApiData(
        currencies :Currencies, currencyConversionValues : CurrencyConversionValues
    ) : MasterApiData {
        return MasterApiData(currencies,currencyConversionValues)
    }

    fun getFakeApiCurriencies() : Currencies {
        val data = FileUtil.kotlinReadFileWithNewLineFromResources(CURRENCIES_FILE)
        return Gson().fromJson(data, Currencies::class.java)
    }

    fun getFakeApiCurriencyConversionValues() : CurrencyConversionValues {
        val data = FileUtil.kotlinReadFileWithNewLineFromResources(CURRENCY_CONVERSION_VALUES_FILE)
        return Gson().fromJson(data, CurrencyConversionValues::class.java)
    }

}

object FakeApiKeyValuePairs {
    const val currencyBase = "USD"

    const val currencyKeyAED = "AED"
    const val currencyValueAED = "United Arab Emirates Dirham"

    // First values for present in response
    const val firstCurrencyKey = "AED"
    const val firstCurrencyValue = "United Arab Emirates Dirham"
    const val firstRatesKey = "AED"
    const val firstRatesValue = "3.67302"
    // First values for present in response
}