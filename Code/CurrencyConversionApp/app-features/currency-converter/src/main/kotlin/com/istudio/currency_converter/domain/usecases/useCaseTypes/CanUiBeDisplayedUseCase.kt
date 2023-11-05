package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class CanUiBeDisplayedUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
) : Action<HashMap<String,Boolean>, Result<Boolean>>() {

    companion object {
        const val keyIsCurrienciesDisplayed = "keyIsCurrienciesDisplayed"
        const val keyIsCurriencyRatesDisplayed = "keyIsCurriencyRatesDisplayed"
    }

    override suspend fun action(input: HashMap<String, Boolean>): Result<Boolean>  =
        withContext(context = dispatcher) {
            try {

                val isCurrienciesDisplayed = input[keyIsCurrienciesDisplayed]
                val isCurriencyRatesDisplayed = input[keyIsCurriencyRatesDisplayed]

                if(isCurrienciesDisplayed == true && isCurriencyRatesDisplayed == true){
                    return@withContext Result.success(true)
                }else{
                    return@withContext Result.success(false)
                }

            }catch (ex:Exception){
                // Return failure with exception
                return@withContext Result.failure(ex)
            }
        }

}