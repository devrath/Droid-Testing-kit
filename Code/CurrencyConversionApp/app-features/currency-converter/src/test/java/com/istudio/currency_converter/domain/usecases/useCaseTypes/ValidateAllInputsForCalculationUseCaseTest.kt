package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isFalse
import assertk.assertions.isTrue
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
import com.istudio.mock_factory.generators.FakeCurrencyAndRates.currencyAndRateList
import com.istudio.models.custom.CurrencyValidationInput
import com.istudio.models.local.CurrencyAndRates
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class ValidateAllInputsForCalculationUseCaseTest {

    //SUT
    private lateinit var sut : ValidateAllInputsForCalculationUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        sut = ValidateAllInputsForCalculationUseCase(dispatcher = testDispatcher)
    }


    @Test
    fun `Test full validation when the user has entered all the fields`() = runTest {
        // <---------- ARRANGE ---------->
        val currencyRates : List<CurrencyAndRates> = currencyAndRateList().apply {
            this[0].rates.isItemSelected.value=true
        }
        val input = CurrencyValidationInput(
            userEnteredCurrencyValueInput = "123",
            userEnteredCurrencyTypeInput = FakeApiKeyValuePairs.firstRatesValue,
            userSelectedCurrencyConversionTypeInput = currencyRates
        )

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isTrue()
        }
    }


    @Test
    fun `Test full validation when the user has not entered currency field`() = runTest {
        // <---------- ARRANGE ---------->
        val currencyRates : List<CurrencyAndRates> = currencyAndRateList().apply {
            this[0].rates.isItemSelected.value=true
        }
        val input = CurrencyValidationInput(
            userEnteredCurrencyValueInput = "",
            userEnteredCurrencyTypeInput = FakeApiKeyValuePairs.firstRatesValue,
            userSelectedCurrencyConversionTypeInput = currencyRates
        )

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isFalse()
        }
    }


    @Test
    fun `Test full validation when the user has not selected currency type field`() = runTest {
        // <---------- ARRANGE ---------->
        val currencyRates : List<CurrencyAndRates> = currencyAndRateList().apply {
            this[0].rates.isItemSelected.value=true
        }
        val input = CurrencyValidationInput(
            userEnteredCurrencyValueInput = "123",
            userEnteredCurrencyTypeInput = "",
            userSelectedCurrencyConversionTypeInput = currencyRates
        )

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isFalse()
        }
    }


    @Test
    fun `Test full validation when the user has not selected rate of conversion`() = runTest {
        // <---------- ARRANGE ---------->
        val currencyRates : List<CurrencyAndRates> = currencyAndRateList().apply {
            this[0].rates.isItemSelected.value=false
        }
        val input = CurrencyValidationInput(
            userEnteredCurrencyValueInput = "123",
            userEnteredCurrencyTypeInput = FakeApiKeyValuePairs.firstRatesValue,
            userSelectedCurrencyConversionTypeInput = currencyRates
        )

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map {
            assertThat(it).isFalse()
        }
    }



}