package com.istudio.database.data.repository

import com.google.common.truth.Truth
import com.istudio.database.fakes.CurrencyDbFeaturesFake
import com.istudio.database.testUtils.MainCoroutineExtension
import com.istudio.mock_factory.generators.FakeCurriencies
import com.istudio.mock_factory.generators.FakeRates
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch

@ExtendWith(MainCoroutineExtension::class)
class CurrencyDbRepositoryTest {

    // SUT
    private lateinit var currencyDbRepository : CurrencyDbRepository
    private lateinit var currencyDbRepositoryMock : CurrencyDbRepository

    @BeforeEach
    fun setUp() {
        // Normal class
        currencyDbRepository = CurrencyDbRepository(features = CurrencyDbFeaturesFake())
        // Mocked class
        currencyDbRepositoryMock = mockk(relaxed = true)
    }

    @AfterEach
    fun tearDown() {}

    @Test
    fun getCurrencies() = runTest {
        // <---------- ARRANGE ---------->
        // Prepare the list of currencies
        val noOfElementsWeInsert = 4

        // <---------- ACT ---------->
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            currencyDbRepository.getCurrencies().collect {
                // <---------- ASSERT ---------->
                //Check the number of items inserted is equal to what we expect
                Truth.assertThat(it.size).isEqualTo(noOfElementsWeInsert)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun addCurrency() = runTest {
        // <---------- ARRANGE ---------->
        val currencyToAdd = FakeCurriencies.currency1

        // <---------- ACT ---------->
        // call the add currency function
        currencyDbRepositoryMock.addCurrency(currencyToAdd)

        // <---------- ASSERT ---------->
        // Check if the function is called
        coVerify { currencyDbRepositoryMock.addCurrency(currencyToAdd) }
    }

    @Test
    fun addRates() = runTest {
        // <---------- ARRANGE ---------->
        val rateToAdd = FakeRates.rates1

        // <---------- ACT ---------->
        // call the add currency function
        currencyDbRepositoryMock.addRates(rateToAdd)

        // <---------- ASSERT ---------->
        // Check if the function is called
        coVerify { currencyDbRepositoryMock.addRates(rateToAdd) }
    }

    @Test
    fun getCurrencyAndRates() = runTest {
        // <---------- ARRANGE ---------->
        // Prepare the list of currencies
        val noOfElementsWeInsert = 4

        // <---------- ACT ---------->
        val items =  currencyDbRepository.getCurrencyAndRates()

        // <---------- ASSERT ---------->
        //Check the number of items inserted is equal to what we expect
        Truth.assertThat(items.size).isEqualTo(noOfElementsWeInsert)
    }
}