package com.istudio.common.generators

import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity

fun fakeCurrency() : CurrencyEntity {
    return CurrencyEntity(
        currencyKey = "USD",
        currencyName = "United states Dollar"
    )
}

fun fakeRate() : RatesEntity {
    return RatesEntity(
        ratesKey = "USD",
        ratesValue = 20.9,
    )
}

object FakeCurriencies{

    val currency1 = fakeCurrency().copy(currencyKey = "USD",currencyName = "United states dollar")
    val currency2 = fakeCurrency().copy(currencyKey = "AED",currencyName = "United Arab Emirates Dirham")
    val currency3 = fakeCurrency().copy(currencyKey = "AFN",currencyName = "Afghan Afghani")
    val currency4 = fakeCurrency().copy(currencyKey = "ALL",currencyName = "Albanian Lek")

    fun currencyList() = listOf(currency1,currency2,currency3,currency4)
}

object FakeRates{
    val rates1 = fakeRate().copy(ratesKey = "USD",ratesValue = 83.7)
    val rates2 = fakeRate().copy(ratesKey = "AED",ratesValue = 3.67302)
    val rates3 = fakeRate().copy(ratesKey = "AFN",ratesValue = 72.855147)
    val rates4 = fakeRate().copy(ratesKey = "ALL",ratesValue = 97.904048)

    fun ratesList() = listOf(rates1,rates2,rates3,rates4)
}

object FakeCurrencyAndRates{
    val currencyAndRates1 = CurrencyAndRates(
        currency = FakeCurriencies.currency1, rates = FakeRates.rates1
    )
    val currencyAndRates2 = CurrencyAndRates(
        currency = FakeCurriencies.currency2, rates = FakeRates.rates2
    )
    val currencyAndRates3 = CurrencyAndRates(
        currency = FakeCurriencies.currency3, rates = FakeRates.rates3
    )
    val currencyAndRates4 = CurrencyAndRates(
        currency = FakeCurriencies.currency4, rates = FakeRates.rates4
    )

    fun currencyAndRateList() = listOf(
        currencyAndRates1,currencyAndRates2,currencyAndRates3,currencyAndRates4
    )
}

