package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.google.gson.Gson
import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.common.platform.extensions.serializeToMap
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.Rates
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertDataIntoDbUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val gson : Gson,
    private val repoController : RepositoryControllerFeatures
) : Action<MasterApiData, Result<MasterApiData>>() {
    override suspend fun action(input: MasterApiData): Result<MasterApiData> = withContext(context = dispatcher) {

        val curriencies : Currencies = input.currencies
        val rates : Rates = input.conversionValues.rates

        val currienciesMapped: Map<String, Any> = curriencies.serializeToMap(gson)
        val ratesMapped: Map<String, Any> = rates.serializeToMap(gson)

        currienciesMapped.mapValues { currencyMap ->
            val key = currencyMap.key
            val value = currencyMap.value

            println(key)
            println(value)
        }

        ratesMapped.mapValues { ratesMap ->
            val key = ratesMap.key
            val value = ratesMap.value

            println(key)
            println(value)
        }


        return@withContext Result.success(input)

    }


}