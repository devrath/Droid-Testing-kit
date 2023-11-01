package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.models.custom.MasterApiData
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class InsertDataIntoDbUseCase @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
    private val repoController : RepositoryControllerFeatures
) : Action<Unit, Result<MasterApiData>>() {
    override suspend fun action(input: Unit): Result<MasterApiData> = withContext(context = dispatcher) {


        TODO("Not yet implemented")

    }


}