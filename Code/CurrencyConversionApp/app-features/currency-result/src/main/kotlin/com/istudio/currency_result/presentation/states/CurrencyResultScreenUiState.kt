package com.istudio.currency_result.presentation.states

import com.istudio.models.custom.CurrencyResultInput

data class CurrencyResultScreenUiState(
    // This flg is set once - > From launched effect state, To start collecting emits only once
    val launchedEffectState: Boolean = false,
    val inputData: CurrencyResultInput?= null,
)
