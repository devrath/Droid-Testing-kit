package com.istudio.network.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
import com.istudio.network.fakes.CurrencyApiFake
import com.istudio.network.testUtils.MainCoroutineExtension
import kotlinx.coroutines.test.runTest

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class CurrencyApiImplTest {

    // SUT
    private lateinit var sut: CurrencyApiImpl

    @BeforeEach
    fun setUp() {
        sut = CurrencyApiImpl(api = CurrencyApiFake())
    }

    @Test
    fun getCurrencies() = runTest {
        // <---------- ARRANGE ---------->
        val currencyName = FakeApiKeyValuePairs.currencyValueAED
        // <---------- ACT ---------->
        val currencyList = sut.getCurrencies()
        // <---------- ASSERT ---------->
        assertThat(currencyList.AED).isEqualTo(currencyName)
    }

    @Test
    fun getCurrencyConversionValues() = runTest {
        // <---------- ARRANGE ---------->
        val currencyBase = FakeApiKeyValuePairs.currencyBase
        // <---------- ACT ---------->
        val currencyConversionValues = sut.getCurrencyConversionValues(currencyBase)
        // <---------- ASSERT ---------->
        assertThat(currencyConversionValues.base).isEqualTo(currencyBase)
    }
}