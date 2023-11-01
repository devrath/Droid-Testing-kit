package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetApiDataUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repoController : RepositoryControllerFeatures
) : Action<Unit, MasterApiData>() {
   override suspend fun action(input: Unit): MasterApiData = withContext(context = dispatcher) {
       val currencies = async { repoController.getCurrencies() }.await()
       val conversionValues = async { repoController.getCurrencyConversionValues() }.await()

       return@withContext MasterApiData(
           currencies = currencies,
           conversionValues = conversionValues
       )
   }
}
