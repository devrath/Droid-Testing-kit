package com.istudio.code.states

import androidx.compose.runtime.MutableState

sealed class AppScreenViewEvent {
    object LoadingState : AppScreenViewEvent()
    object CheckConnectivity : AppScreenViewEvent()
    object LoadFromDatabase : AppScreenViewEvent()
    object ToggleDataSource : AppScreenViewEvent()
    data class ToolbarVisibility(val isVisible : Boolean) : AppScreenViewEvent()
    data class IsActionButtonVisible(val isVisible : Boolean) : AppScreenViewEvent()
    data class SetToolBarTitle(val title : String) : AppScreenViewEvent()
    // Screen Orientation is updated so composable can display appropriate screens
    data class SetScreenOrientation(val orientation : Int) : AppScreenViewEvent()
    // Handle exit alert display
    data class HandleExitAlertDisplay(val isExitAlertDisplayed : MutableState<Boolean>) : AppScreenViewEvent()
    // Set message for hte error to be displayed
    data class SetMessageForError(val message : String) : AppScreenViewEvent()
    // Handle error alert display
    data class HandleErrorAlertDisplay(val isErrorAlertDisplayed : MutableState<Boolean>) : AppScreenViewEvent()
}
