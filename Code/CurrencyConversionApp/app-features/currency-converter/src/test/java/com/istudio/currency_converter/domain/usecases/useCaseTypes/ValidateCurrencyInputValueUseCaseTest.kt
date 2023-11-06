package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isEqualTo
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MainCoroutineExtension::class)
class ValidateCurrencyInputValueUseCaseTest {

    //SUT
    private lateinit var sut : ValidateCurrencyInputValueUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        sut = ValidateCurrencyInputValueUseCase(dispatcher = testDispatcher)
    }

    @Test
    fun `Test currency input is valid`() = runTest {
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
    fun `Test currency input is in-valid`() = runTest {
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