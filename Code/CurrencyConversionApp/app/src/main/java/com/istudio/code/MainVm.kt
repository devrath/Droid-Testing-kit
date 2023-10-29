package com.istudio.code

import androidx.compose.runtime.mutableStateOf
import com.istudio.core.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVm @Inject constructor(

): BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) {}

    val currentTheme = mutableStateOf(false)


}