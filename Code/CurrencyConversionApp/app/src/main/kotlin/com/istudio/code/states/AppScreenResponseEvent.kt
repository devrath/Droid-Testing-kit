package com.istudio.code.states

sealed class AppScreenResponseEvent {

    // object ToggleData : AppScreenResponseEvent()
    //data class Connectivity(val isConnected: Boolean) : AppScreenResponseEvent()
    data class ShowSnackBar(val message: String) : AppScreenResponseEvent()
    data class ToggleData(val isFetchFromServer: Boolean) : AppScreenResponseEvent()

}
