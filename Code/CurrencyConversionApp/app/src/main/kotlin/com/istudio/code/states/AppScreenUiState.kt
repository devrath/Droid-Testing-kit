package com.istudio.code.states

import android.content.res.Configuration

data class AppScreenUiState(
    // By default - It is loading state
    val loadingState : Boolean = true,
    val isConnectedToInternet : Boolean = false,
    val launchedEffectState : Boolean = false,
    val isToolbarVisible : Boolean = false,
    val isActionButtonVisible : Boolean = false,
    val toolBarTitle : String = "",
    val isExitAlertDisplayed : Boolean = false,
    val orientation : Int = Configuration.ORIENTATION_PORTRAIT
)
