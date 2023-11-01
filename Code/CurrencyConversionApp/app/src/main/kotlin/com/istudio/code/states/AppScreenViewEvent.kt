package com.istudio.code.states

sealed class AppScreenViewEvent {
    object LoadingState : AppScreenViewEvent()
    object CheckConnectivity : AppScreenViewEvent()
    data class ToolbarVisibility(val isVisible : Boolean) : AppScreenViewEvent()
}
