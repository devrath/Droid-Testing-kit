package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext
import javax.inject.Inject

class GetCurrencyRatesListDataFromDbUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repoController: RepositoryControllerFeatures
) : Action<Unit, Result<List<CurrencyAndRates>>>() {

    override suspend fun action(input: Unit): Result<List<CurrencyAndRates>> =
        withContext(context = dispatcher) {
            try {
                val result = repoController.getCurrencyAndRates()
                return@withContext Result.success(result)
            }catch (ex:Exception){
                // Return failure with exception
                return@withContext Result.failure(ex)
            }
        }

}