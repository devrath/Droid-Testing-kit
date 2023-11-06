package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class ValidateCurrencyInputTypeUseCaseTest {

    //SUT
    private lateinit var sut : ValidateCurrencyInputTypeUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        sut = ValidateCurrencyInputTypeUseCase(dispatcher = testDispatcher)
    }

    @Test
    fun `Test currency type input is valid`() = runTest {
        // <---------- ARRANGE ---------->
        val valueToTest = ""

        // <---------- ACT ---------->
        val result = sut.invoke(valueToTest)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isFalse()
        }
    }

    @Test
    fun `Test currency type input is in-valid`() = runTest {
        // <---------- ARRANGE ---------->
        val valueToTest = "123"

        // <---------- ACT ---------->
        val result = sut.invoke(valueToTest)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isTrue()
        }
    }

}