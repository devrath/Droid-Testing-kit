package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDataFromNetworkUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repoController : RepositoryControllerFeatures
) : Action<Unit, Result<MasterApiData>>() {
   override suspend fun action(input: Unit): Result<MasterApiData> = withContext(context = dispatcher) {
       try {
           // Currencies
           val currencies = async { repoController.getCurrencies() }.await()
           // Currency Values
           val conversionValues = async { repoController.getCurrencyConversionValues() }.await()
           // Final result  = Currencies + Currency Values
           val result = MasterApiData(
               currencies = currencies,
               conversionValues = conversionValues
           )
           // Return result
           return@withContext Result.success(result)
       }catch (exception:Exception){
           // Return failure with exception
           return@withContext Result.failure(exception)
       }
   }
}
