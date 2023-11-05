package com.istudio.database.generators

import com.istudio.models.local.CurrencyEntity

fun currencyDummy() : CurrencyEntity {
    return CurrencyEntity(
        currencyKey = "USD",
        currencyName = "United states Dollar"
    )
}