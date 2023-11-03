package com.istudio.code.states

data class AppScreenUiState(
    // By default - It is loading state
    val loadingState : Boolean = true,
    val isConnectedToInternet : Boolean = false,
    val launchedEffectState : Boolean = false,
    val isToolbarVisible : Boolean = false,
    val isActionButtonVisible : Boolean = false,
    val toolBarTitle : String = "",
)
