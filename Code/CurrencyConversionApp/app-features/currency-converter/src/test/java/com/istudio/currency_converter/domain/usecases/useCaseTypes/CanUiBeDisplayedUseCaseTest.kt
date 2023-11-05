package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase.Companion.keyIsCurrienciesDisplayed
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase.Companion.keyIsCurriencyRatesDisplayed
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.CsvSource
import org.junit.jupiter.params.provider.ValueSource

@ExtendWith(MainCoroutineExtension::class)
class CanUiBeDisplayedUseCaseTest {

    // SUT
    private lateinit var sut : CanUiBeDisplayedUseCase

    @BeforeEach
    fun setUp() {
        sut = CanUiBeDisplayedUseCase(dispatcher = Dispatchers.Main)
    }

    @Test
    fun `Test UI can be displayed`() = runTest {
        // <---------- ARRANGE ---------->
        val input = HashMap<String,Boolean>().apply {
            put(keyIsCurrienciesDisplayed,true)
            put(keyIsCurriencyRatesDisplayed,true)
        }
        // <---------- ACT ---------->
        val result = sut.invoke(input)

        result.map {

            // <---------- ASSERT ---------->
            assertThat(it).isTrue()
        }
    }


    @ParameterizedTest
    @CsvSource("false,true","true,false")
    fun `Test UI should not be displayed`(
        valueIsCurrienciesDisplayed : Boolean, valueIsCurriencyRatesDisplayed : Boolean
    ) = runTest {
        // <---------- ARRANGE ---------->
        val input = HashMap<String,Boolean>().apply {
            put(keyIsCurrienciesDisplayed,valueIsCurrienciesDisplayed)
            put(keyIsCurriencyRatesDisplayed,valueIsCurriencyRatesDisplayed)
        }
        // <---------- ACT ---------->
        val result = sut.invoke(input)
        result.map {

            // <---------- ASSERT ---------->
            assertThat(it).isFalse()
        }
    }


}