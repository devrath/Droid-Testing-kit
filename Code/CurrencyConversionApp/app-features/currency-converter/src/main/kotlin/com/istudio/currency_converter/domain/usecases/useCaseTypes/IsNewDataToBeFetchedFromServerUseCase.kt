package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.MasterApiData
import com.istudio.preferences.data.RepositoryPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class IsNewDataToBeFetchedFromServerUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val preferences : RepositoryPreferences
) : Action<Unit, Result<Boolean>>() {
    override suspend fun action(input: Unit): Result<Boolean> = withContext(context = dispatcher) {
        try {
            val isDataSavedInDatabase = preferences.getIsDataSaved()
            if(isDataSavedInDatabase!=null){
                if(isDataSavedInDatabase == true){
                    // Return result: -> Fetch from DB -> Cache data is available
                    return@withContext Result.success(false)
                }else{
                    // Return result: -> Fetch new data Network
                    return@withContext Result.success(true)
                }
            }else{
                // Return result
                return@withContext Result.success(true)
            }
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}