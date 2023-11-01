package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.MasterApiData
import com.istudio.preferences.data.RepositoryPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SaveTimeStampUseCase@Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val preferences : RepositoryPreferences
) : Action<MasterApiData, Result<Unit>>() {
    override suspend fun action(input: MasterApiData): Result<Unit> = withContext(context = dispatcher) {
        try {
            preferences.apply {
                saveTimeStamp(input.conversionValues.timestamp.toString())
                saveIsDataSaved(true)
            }
            // Return result
            return@withContext Result.success(Unit)
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}