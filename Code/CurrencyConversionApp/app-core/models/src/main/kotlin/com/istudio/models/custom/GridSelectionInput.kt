package com.istudio.models.custom

import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.RatesEntity

data class GridSelectionInput(
    val data: List<CurrencyAndRates>,
    val selectedPosition: Int
)
