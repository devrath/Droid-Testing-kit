package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.istudio.common.platform.actions.Action
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.models.custom.CurrencyValidationInput
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.withContext
import javax.inject.Inject

class ValidateCurrencyInput @Inject constructor(
    @IoDispatcher private val dispatcher: CoroutineDispatcher
) : Action<CurrencyValidationInput, Result<Boolean>>() {

    override suspend fun action(input: CurrencyValidationInput): Result<Boolean> = withContext(context = dispatcher) {

        try {
            // Text Field - Input
            val userEnteredCurrencyValueInput : String = input.userEnteredCurrencyValueInput
            // Drop down menu - Input
            val userEnteredCurrencyTypeInput : String = input.userEnteredCurrencyTypeInput
            // Grid Selection - Input
            val userSelectedCurrencyConversionTypeInput : List<RatesEntity> = input.userSelectedCurrencyConversionTypeInput

            if(userEnteredCurrencyValueInput.isNotEmpty()){
                // Text Field - Input --- Validation ---> Success
                if(userEnteredCurrencyTypeInput.isNotEmpty()){
                    // Drop down menu - Input --- Validation ---> Success
                    var isAtLeastOneItemSelected = false
                    userSelectedCurrencyConversionTypeInput.forEachIndexed { index, ratesEntity ->
                        if(ratesEntity.isItemSelected.value){
                            isAtLeastOneItemSelected = true
                        }
                    }
                    if(isAtLeastOneItemSelected){
                        // Grid Selection - Input --- Validation ---> Success
                        return@withContext Result.success(true)
                    }else{
                        // Grid Selection - False --- Validation ---> Success
                        val errorMessage = "Please select currency conversion type to be converted into"
                        return@withContext Result.failure(Exception(errorMessage))
                    }
                }else{
                    // Drop down menu - Input --- Validation ---> Failure
                    val errorMessage = "Please select currency conversion type from drop down menu "
                    return@withContext Result.failure(Exception(errorMessage))
                }

            }else{
                // Text Field - Input --- Validation ---> Failure
                val errorMessage = "Please enter currency value to convert"
                return@withContext Result.failure(Exception(errorMessage))
            }
        }catch (exception:Exception){
            // Return failure with exception
            return@withContext Result.failure(exception)
        }
    }
}