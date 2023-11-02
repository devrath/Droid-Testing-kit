package com.istudio.models.custom

import com.istudio.models.local.RatesEntity

data class GridSelectionInput(
    val data: List<RatesEntity>,
    val selectedPosition: Int
)
