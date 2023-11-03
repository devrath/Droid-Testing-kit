package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.MasterApiData
import com.istudio.preferences.data.RepositoryPreferences
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale
import javax.inject.Inject

class IsNewDataToBeFetchedFromServerUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val preferences : RepositoryPreferences
) : Action<Unit, Result<Boolean>>() {

    companion object {
        const val minutesDurationToFetchCachedData = 30
    }

    override suspend fun action(input: Unit): Result<Boolean> = withContext(context = dispatcher) {
        try {
            if(getStoredTimeStamp().isNotEmpty()){
                // Subsequent app launches
                if(shouldFetchCachedData()){
                    // get from local database
                    // Return result: -> Fetch from DB -> Cache data is available
                    return@withContext Result.success(false)
                }else{
                    // Get from Server : With additional validation checking that data is stored or not
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
                }
            }else{
                // First time app launch
                // Return result: -> Fetch new data Network
                return@withContext Result.success(true)
            }
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }

    private suspend fun shouldFetchCachedData() : Boolean {
        val storedTimeStamp = getStoredTimeStamp()
        val storedMinutes = Timestamp(storedTimeStamp.toLong()).minutes
        val currentMinutes = Timestamp(System.currentTimeMillis()).minutes
        return (storedMinutes-currentMinutes)<minutesDurationToFetchCachedData
    }

    private suspend fun getStoredTimeStamp() = preferences.getTimeStamp() ?: ""
}