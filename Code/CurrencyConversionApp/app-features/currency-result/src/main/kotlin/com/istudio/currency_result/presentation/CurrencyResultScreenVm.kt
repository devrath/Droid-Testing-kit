package com.istudio.currency_result.presentation

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.viewModelScope
import com.istudio.common.platform.coroutines.dispatcher.MainDispatcher
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_result.presentation.states.CurrencyResultScreenResponseEvent
import com.istudio.currency_result.presentation.states.CurrencyResultScreenUiState
import com.istudio.currency_result.presentation.states.CurrencyResultScreenViewEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CurrencyResultScreenVm @Inject constructor(
    @MainDispatcher val mainDispatcher : CoroutineDispatcher,
) : BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) { }
    // Holds the data of all the widgets in the view
    var viewState: MutableState<CurrencyResultScreenUiState> = mutableStateOf(CurrencyResultScreenUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<CurrencyResultScreenResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: CurrencyResultScreenViewEvent) {
        viewModelScope.launch {
            when (event) {
                is CurrencyResultScreenViewEvent.SetResultDataInVm -> {
                    viewState.value = viewState.value.copy(inputData = event.data)
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/



}