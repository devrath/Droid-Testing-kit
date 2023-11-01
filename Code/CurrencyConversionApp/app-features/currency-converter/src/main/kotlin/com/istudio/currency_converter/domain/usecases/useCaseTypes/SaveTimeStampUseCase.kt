package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.MasterApiData
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SaveTimeStampUseCase@Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher,
   // val RepositoryPreferences : RepositoryPreferences
) : Action<MasterApiData, Result<Boolean>>() {
    override suspend fun action(input: MasterApiData): Result<Boolean> {
        TODO("Not yet implemented")
    }

}