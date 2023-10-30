package com.istudio.core.platform.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.SupervisorJob
import kotlinx.coroutines.launch

abstract class BaseViewModel<in INPUT_FROM_VIEW> : ViewModel() {

    internal val tag = this.javaClass.simpleName

    abstract fun setupPrerequisites(params: INPUT_FROM_VIEW)

    internal inline fun ViewModel.launchUseCase(
        handler: CoroutineExceptionHandler,
        crossinline block: suspend () -> Unit
    ): Job = viewModelScope.launch(handler) { block() }

    internal inline fun ViewModel.catchException(crossinline block: (exception: Throwable) -> Unit): CoroutineExceptionHandler {
        return CoroutineExceptionHandler { _, exception ->
            block(exception)
        }
    }


    /** ************************** View-Model-Scoping ************************** **/
    /**
     * This is the job for all coroutines started by this ViewModel.
     * Cancelling this job will cancel all coroutines started by this ViewModel.
     */
    private val viewModelJob = SupervisorJob()

    /**
     * This is the main scope for all coroutines launched by MainViewModel.
     * Since we pass viewModelJob, you can cancel all coroutines
     * launched by uiScope by calling viewModelJob.cancel()
     */
    val uiScope = CoroutineScope(Dispatchers.Main + viewModelJob)

    /**
     * Cancel all coroutines when the ViewModel is cleared
     */
    override fun onCleared() {
        super.onCleared()
        viewModelJob.cancel()
    }
    /** ************************** View-Model-Scoping ************************** **/
}

/*internal inline fun ifNoInternet(crossinline block: () -> Unit) {
    block()
}*/
