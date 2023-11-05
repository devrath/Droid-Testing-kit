package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateCurrencyInputTypeUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Action<String, Result<Boolean>>() {

    override suspend fun action(input: String): Result<Boolean> = withContext(context = dispatcher) {
        try {
            if(input.isNotEmpty()){
                return@withContext Result.success(true)
            }else{
                return@withContext Result.success(false)
            }
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}