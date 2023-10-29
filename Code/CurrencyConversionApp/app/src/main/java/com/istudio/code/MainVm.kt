package com.istudio.code

import androidx.compose.runtime.mutableStateOf
import com.istudio.common_feature.data.usecases.access.CommonFeaturesUseCases
import com.istudio.core.platform.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainVm @Inject constructor(
    private val commonFeatureUseCases : CommonFeaturesUseCases
): BaseViewModel<Unit>() {
    override fun setupPrerequisites(params: Unit) {}

    val currentTheme = mutableStateOf(false)



    private fun checkConnectivity(){
        val result = commonFeatureUseCases.connectivity.invoke()
    }


}