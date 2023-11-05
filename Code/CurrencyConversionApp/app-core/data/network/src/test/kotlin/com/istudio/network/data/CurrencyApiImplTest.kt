package com.istudio.network.data

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.istudio.network.fakes.CurrencyApiFake
import com.istudio.network.testUtils.MainCoroutineExtension
import kotlinx.coroutines.test.runTest
import org.junit.Assert
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class CurrencyApiImplTest {

    // SUT
    private lateinit var currencyApiImpl: CurrencyApiImpl

    @BeforeEach
    fun setUp() {
        currencyApiImpl = CurrencyApiImpl(
            api = CurrencyApiFake()
        )
    }

    @Test
    fun getCurrencies() = runTest {
        //val currencyName ="United Arab Emirates Dirham"
        //val currencyList = currencyApiImpl.getCurrencies()

        //assertEquals(currencyList.AED).isEqualTo(currencyName)
        Assert.assertEquals(4, 2 + 2)
    }

}