package com.istudio.code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.viewModelScope
import com.istudio.code.states.AppScreenResponseEvent
import com.istudio.code.states.AppScreenUiState
import com.istudio.code.states.AppScreenViewEvent
import com.istudio.common.platform.functional.UseCaseResult
import com.istudio.common.platform.uiEvent.UiText
import com.istudio.common.platform.viewmodel.BaseViewModel
import com.istudio.currency_converter.domain.usecases.CommonFeaturesUseCases
import com.istudio.currency_converter.presentation.states.CurrencyScreenResponseEvent
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.receiveAsFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

// viewModelScope: https://medium.com/androiddevelopers/easy-coroutines-in-android-viewmodelscope-25bffb605471
@HiltViewModel
class MainVm @Inject constructor(
    private val commonFeatureUseCases : CommonFeaturesUseCases
): BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) { }

    val currentTheme = mutableStateOf(false)

    // Holds the data of all the widgets in the view
    var viewState by mutableStateOf(AppScreenUiState())
        private set

    // View model sets this state, Composable observes this state
    private val _uiEvent = Channel<AppScreenResponseEvent>()
    val uiEvent = _uiEvent.receiveAsFlow()

    /** <************> UI Action is invoked from composable <************> **/
    fun onEvent(event: AppScreenViewEvent) {
        viewModelScope.launch {
            when (event) {
                is AppScreenViewEvent.CheckConnectivity -> {
                    viewState = viewState.copy(loadingState = true)
                    checkConnectivity()
                }

                is AppScreenViewEvent.LoadingState -> {
                    viewState = viewState.copy(loadingState = true)
                }

                is AppScreenViewEvent.ToolbarVisibility -> {
                    viewState = viewState.copy(isToolbarVisible = event.isVisible)
                }

                is AppScreenViewEvent.ToggleDataSource -> {
                    toggleData()
                }

                is AppScreenViewEvent.LoadFromDatabase -> {
                    // Start displaying of data from local database
                    viewState = viewState.copy(
                        loadingState = false, isConnectedToInternet = true
                    )
                }
            }
        }
    }
    /** <************> UI Action is invoked from composable <************> **/

    private fun checkConnectivity()  = uiScope.launch {
        // Invoke the use-case
        val result = commonFeatureUseCases.connectivity.invoke(Unit)
        // Collect result from use-case

        result.catch {
            displayErrorInSnackBar(UiText.DynamicString(it.message.toString()))
        }.collect{ result ->
            withContext(Dispatchers.Default) {
                // Keep the UI-state
                viewState = viewState.copy(
                    loadingState = false,
                    isConnectedToInternet = result
                )
            }
        }

    }


    /**
     * USE-CASE :----> Toggle data from DB and Server
     */
    private fun toggleData() = uiScope.launch {
        try {
            val result = commonFeatureUseCases.IsNewDataToBeFetchedFromServerUseCase.invoke(Unit)
            if(result.isSuccess){
                result.map {
                    _uiEvent.send(AppScreenResponseEvent.ToggleData(it))
                }
            }else{
                displayErrorInSnackBar(UiText.DynamicString("Error in getting data from preferences"))
            }
        }catch (ex:Exception){
            displayErrorInSnackBar(UiText.DynamicString(ex.message.toString()))
        }
    }


    /** ********************************* DISPLAY MESSAGES ****************************************/
    /**
     * ERROR HANDLING:
     * Displaying messages to the snack-bar
     */
    private suspend fun displayErrorInSnackBar(result: UiText?) {
       result?.let { _uiEvent.send(AppScreenResponseEvent.ShowSnackBar(it.toString())) }
    }

    /**
     * ERROR HANDLING:
     * For the Use cases
     */
    private suspend fun useCaseError(result: UseCaseResult.Error) {
        val uiEvent = UiText.DynamicString(result.exception.message.toString())
        //_uiEvent.send(AddBookResponseEvent.ShowSnackBar(uiEvent.text))
    }

    /** ********************************* DISPLAY MESSAGES ****************************************/
}