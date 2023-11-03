package com.istudio.code.states

sealed class AppScreenViewEvent {
    object LoadingState : AppScreenViewEvent()
    object CheckConnectivity : AppScreenViewEvent()
    object LoadFromDatabase : AppScreenViewEvent()
    object ToggleDataSource : AppScreenViewEvent()
    data class ToolbarVisibility(val isVisible : Boolean) : AppScreenViewEvent()
    data class IsActionButtonVisible(val isVisible : Boolean) : AppScreenViewEvent()
    data class SetToolBarTitle(val title : String) : AppScreenViewEvent()
}
