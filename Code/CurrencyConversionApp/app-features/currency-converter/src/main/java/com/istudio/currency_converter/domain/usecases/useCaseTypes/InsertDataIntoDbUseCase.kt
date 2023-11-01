package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.google.gson.Gson
import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.common.platform.extensions.serializeToMap
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.Rates
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertDataIntoDbUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val gson: Gson,
    private val repoController: RepositoryControllerFeatures
) : Action<MasterApiData, Result<Unit>>() {
    override suspend fun action(input: MasterApiData): Result<Unit> =
        withContext(context = dispatcher) {

            try {
                // --------- Currencies-Data --------->

                val resultCurriencies = async {
                    val curriencies: Currencies = input.currencies
                    val currienciesMapped: Map<String, Any> = curriencies.serializeToMap(gson)

                    currienciesMapped.mapValues { currencyMap ->
                        val key: String = currencyMap.key
                        val value: String = currencyMap.value as String

                        println("$key $value")

                        repoController.insertCurrencies(
                            CurrencyEntity(currencyKey = key, currencyName = value)
                        )
                    }
                }

                // -------- CurrencyRates-Data --------->
                val resultRates = async {
                    val rates: Rates = input.conversionValues.rates
                    val ratesMapped: Map<String, Any> = rates.serializeToMap(gson)

                    ratesMapped.mapValues { ratesMap ->

                        val key: String = ratesMap.key
                        val value: Double = ratesMap.value as Double

                        println("$key $value")

                        repoController.insertRates(
                            RatesEntity(ratesKey = key, ratesValue = value)
                        )
                    }
                }

                // make the parent co-routine wait until all teh await functions are completed
                awaitAll(resultCurriencies,resultRates)

                return@withContext Result.success(Unit)
            }catch (exception:Exception){
                // Return failure with exception
                return@withContext Result.failure(exception)
            }

        }

}