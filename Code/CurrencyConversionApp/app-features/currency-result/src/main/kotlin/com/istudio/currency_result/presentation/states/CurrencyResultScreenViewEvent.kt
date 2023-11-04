package com.istudio.currency_result.presentation.states

import com.istudio.models.custom.CurrencyResultInput

sealed class CurrencyResultScreenViewEvent {
    data class SetResultDataInVm(val data: CurrencyResultInput) : CurrencyResultScreenViewEvent()
}
