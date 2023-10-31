package com.istudio.models.custom

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues

data class MasterApiData(
    val currencies : Currencies,
    val conversionValues : CurrencyConversionValues
)
