package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import com.istudio.network.api.CurrencyApi.Companion.DEFAULT_CURRENCY
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetDataFromNetworkUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repoController : RepositoryControllerFeatures
) : Action<String, Result<MasterApiData>>() {
   override suspend fun action(base: String): Result<MasterApiData> = withContext(context = dispatcher) {
       try {
           // Currencies
           val currencies = async { repoController.getCurrenciesFromApi() }.await()
           // Currency Values
           val conversionValues = async { repoController.getCurrencyConversionValuesFromApi(base) }.await()
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
