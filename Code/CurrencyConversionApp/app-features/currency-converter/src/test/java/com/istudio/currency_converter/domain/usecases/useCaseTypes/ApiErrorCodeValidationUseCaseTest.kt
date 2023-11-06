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

        // <---------- ACT ---------->
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.notFoundCode)

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

        // <---------- ACT ---------->
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.invalidAppIdCode)

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

        // <---------- ACT ---------->
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.notAllowedCode)

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

        // <---------- ACT ---------->
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.accessRestrictedCode)

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

        // <---------- ACT ---------->
        val resultOfSut = sut.invoke(ApiErrorCodeValidationUseCase.invalidBaseCode)

        if(resultOfSut.isSuccess){
            resultOfSut.map {

                // <---------- ASSERT ---------->
                assertThat(it).isEqualTo(messageToValidate)
            }
        }
    }

}