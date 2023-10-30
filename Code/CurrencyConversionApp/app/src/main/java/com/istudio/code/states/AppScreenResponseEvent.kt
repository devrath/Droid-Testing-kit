package com.istudio.code.states

sealed class AppScreenResponseEvent {

    // object AppUserInputError : AppScreenResponseEvent()
    //data class Connectivity(val isConnected: Boolean) : AppScreenResponseEvent()
    data class ShowSnackBar(val message: String) : AppScreenResponseEvent()

}
