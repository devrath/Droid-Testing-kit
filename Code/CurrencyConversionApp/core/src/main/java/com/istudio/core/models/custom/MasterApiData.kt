package com.istudio.core.models.custom

import com.istudio.core.models.remote.Currencies
import com.istudio.core.models.remote.CurrencyConversionValues

data class MasterApiData(
    val currencies : Currencies,
    val conversionValues : CurrencyConversionValues
)
