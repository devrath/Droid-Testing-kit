package com.istudio.database.generators

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
}


object FakeRates{
    val rates1 = fakeRate().copy(ratesKey = "USD",ratesValue = 83.7)
    val rates2 = fakeRate().copy(ratesKey = "AED",ratesValue = 3.67302)
    val rates3 = fakeRate().copy(ratesKey = "AFN",ratesValue = 72.855147)
    val rates4 = fakeRate().copy(ratesKey = "ALL",ratesValue = 97.904048)
}

