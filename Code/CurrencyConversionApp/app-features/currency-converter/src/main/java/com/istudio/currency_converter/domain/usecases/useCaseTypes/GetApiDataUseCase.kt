package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.models.custom.MasterApiData
import com.istudio.network.data.CurrencyApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetApiDataUseCase @Inject constructor(
    @com.istudio.common.platform.coroutines.dispatcher.IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api : com.istudio.network.data.CurrencyApiRepository
) : com.istudio.common.platform.actions.Action<Unit, MasterApiData>() {
   override suspend fun action(input: Unit): com.istudio.models.custom.MasterApiData = withContext(context = dispatcher) {
       val currencies = async { api.getCurrencies() }.await()
       val conversionValues = async { api.getCurrencyConversionValues() }.await()
       return@withContext com.istudio.models.custom.MasterApiData(
           currencies = currencies,
           conversionValues = conversionValues
       )
   }
}
