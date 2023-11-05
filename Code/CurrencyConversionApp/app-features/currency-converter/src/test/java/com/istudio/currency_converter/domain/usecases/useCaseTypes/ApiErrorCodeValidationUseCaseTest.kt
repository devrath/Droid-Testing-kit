package com.istudio.currency_converter.domain.usecases.useCaseTypes

import com.google.common.truth.Truth
import com.google.common.truth.Truth.assertThat
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class ApiErrorCodeValidationUseCaseTest {

    private lateinit var sut : ApiErrorCodeValidationUseCase

    @BeforeEach
    fun setUp() {
        sut = ApiErrorCodeValidationUseCase(dispatcher = Dispatchers.Main)
    }

    @Test
    fun `Test notFoundCode for api response`() = runTest {
        // <---------- ARRANGE ---------->
        val messageToValidate = ApiErrorCodeValidationUseCase.notFoundMessage
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.notFoundCode)
        // <---------- ACT ---------->
        if(resultOfSut.isSuccess){
            resultOfSut.map {
                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }


    @Test
    fun `Test invalidAppIdCode for api response`() = runTest {
        // <---------- ARRANGE ---------->
        val messageToValidate = ApiErrorCodeValidationUseCase.invalidAppIdMessage
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.invalidAppIdCode)
        // <---------- ACT ---------->
        if(resultOfSut.isSuccess){
            resultOfSut.map {
                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }

    @Test
    fun `Test notAllowedCode for api response`() = runTest {
        // <---------- ARRANGE ---------->
        val messageToValidate = ApiErrorCodeValidationUseCase.notAllowedMessage
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.notAllowedCode)
        // <---------- ACT ---------->
        if(resultOfSut.isSuccess){
            resultOfSut.map {
                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }

    @Test
    fun `Test accessRestrictedCode for api response`() = runTest {
        // <---------- ARRANGE ---------->
        val messageToValidate = ApiErrorCodeValidationUseCase.accessRestrictedMessage
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.accessRestrictedCode)
        // <---------- ACT ---------->
        if(resultOfSut.isSuccess){
            resultOfSut.map {
                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }

    @Test
    fun `Test invalidBaseCode for api response`() = runTest {
        // <---------- ARRANGE ---------->
        val messageToValidate = ApiErrorCodeValidationUseCase.invalidBaseMessage
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.invalidBaseCode)
        // <---------- ACT ---------->
        if(resultOfSut.isSuccess){
            resultOfSut.map {
                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }

}