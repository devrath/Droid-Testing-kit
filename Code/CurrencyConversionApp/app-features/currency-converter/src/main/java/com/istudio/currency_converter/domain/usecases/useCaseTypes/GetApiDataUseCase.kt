package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.models.custom.MasterApiData
import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.actions.FlowAction
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.CurrencyApiRepository
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.zip
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetApiDataUseCase @Inject constructor(
    @com.istudio.common.platform.coroutines.dispatcher.IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val api : CurrencyApiRepository
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
