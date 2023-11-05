package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ApiErrorCodeValidationUseCase  @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Action<Int, Result<String>>() {

    companion object NetworkStatusErrorCodes {
        // Reference:-> https://docs.openexchangerates.org/reference/errors
        // NotFound
        const val notFoundCode: Int = 404
        const val notFoundMessage: String = "Client requested a non-existent resource/route"
        // InvalidAppId
        const val invalidAppIdCode: Int = 401
        const val invalidAppIdMessage: String = "Invalid App ID"
        // NotAllowed
        const val notAllowedCode: Int = 429
        const val notAllowedMessage: String = "Client doesn’t have permission to access requested route/feature"
        // AccessRestricted
        const val accessRestrictedCode: Int = 400
        const val accessRestrictedMessage: String =
            "Access restricted for repeated over-use (status: 429), or other reason given in ‘description’ (403)."
        // InvalidBase
        const val invalidBaseCode: Int = 403
        const val invalidBaseMessage: String = "Only dollar as a user currency is supported \n\n Please upgrade to a paid service"
    }

    override suspend fun action(input: Int): Result<String> = withContext(context = dispatcher) {
        try {
            var errorMessage = ""
            when (input) {
                notFoundCode -> errorMessage = notFoundMessage
                invalidAppIdCode -> errorMessage = invalidAppIdMessage
                notAllowedCode -> errorMessage = notAllowedMessage
                accessRestrictedCode ->errorMessage = accessRestrictedMessage
                invalidBaseCode -> errorMessage = invalidBaseMessage
            }
            return@withContext Result.success(errorMessage)
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}