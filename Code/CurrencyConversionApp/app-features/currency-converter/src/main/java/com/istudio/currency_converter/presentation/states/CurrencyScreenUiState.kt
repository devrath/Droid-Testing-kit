package com.istudio.currency_converter.presentation.states

data class CurrencyScreenUiState(
    val currencyUserEnteredInput: String = "",
    val currencyUserConversionInput: String = "",

    val currencyUserEnteredInputError: Boolean = false,
    val currencyUserConversionInputError: Boolean = false,

    val launchedEffectState: Boolean = false,
    //val genreList: List<String> = emptyList(),
)
