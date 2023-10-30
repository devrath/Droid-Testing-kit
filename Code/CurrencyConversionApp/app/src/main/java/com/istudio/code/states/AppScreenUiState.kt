package com.istudio.code.states

data class AppScreenUiState(
    val isConnectedToInternet : Boolean = false,
    val launchedEffectState : Boolean = false,
    //val genreList: List<String> = emptyList(),
)
