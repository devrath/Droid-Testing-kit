package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.GridSelectionInput
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class SetRateGridSelectionUseCase  @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Action<GridSelectionInput, Result<List<RatesEntity>>>() {
    override suspend fun action(input: GridSelectionInput): Result<List<RatesEntity>> = withContext(context = dispatcher) {
        try {
            input.data.forEachIndexed { index, ratesEntity ->
                ratesEntity.isItemSelected.value = input.selectedPosition==index
            }
            return@withContext Result.success(input.data)
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}