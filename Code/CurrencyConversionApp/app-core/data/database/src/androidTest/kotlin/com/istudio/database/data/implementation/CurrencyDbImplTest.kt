package com.istudio.database.data.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.google.common.truth.Truth.assertThat
import com.istudio.common.generators.FakeCurriencies
import com.istudio.common.generators.FakeRates
import com.istudio.database.room.CurrencyDatabase
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.database.testUtils.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.cancelAndJoin
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith
import java.util.concurrent.CountDownLatch

@ExtendWith(MainCoroutineExtension::class)
class CurrencyDbImplTest {

    private lateinit var database: CurrencyDatabase
    private lateinit var currencyDao: CurrencyDao

    @BeforeEach
    fun setUp() {
        // Initialize the in-memory database before each test case
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CurrencyDatabase::class.java
        ).allowMainThreadQueries().build()
        // Initilize the DAO for the initialized database
        currencyDao = database.currencyDao()
    }

    @AfterEach
    fun tearDown() {
        // Close the database after each database
        database.close()
    }

    @Test
    fun getCurrencyList_getData_success() = runBlocking {
        // <---------- ARRANGE ---------->
        // Prepare the list of currencies
        val noOfElementsWeInsert = 4
        // Add all the currency one by one to database
        currencyDao.apply {
            addCurrency(FakeCurriencies.currency1)
            addCurrency(FakeCurriencies.currency2)
            addCurrency(FakeCurriencies.currency3)
            addCurrency(FakeCurriencies.currency4)
        }

        // <---------- ACT ---------->
        val latch = CountDownLatch(1)
        val job = async(Dispatchers.IO) {
            currencyDao.getCurrencyList().collect {
                // <---------- ASSERT ---------->
                //Check the number of items inserted is equal to what we expect
                assertThat(it.size).isEqualTo(noOfElementsWeInsert)
                latch.countDown()
            }
        }
        latch.await()
        job.cancelAndJoin()
    }

    @Test
    fun addCurrency() = runTest {
        // <---------- ARRANGE ---------->
        // Prepare the currency row
        val currency = FakeCurriencies.currency1

        // <---------- ACT ---------->
        // Add the currency row to the database using the dao
        currencyDao.addCurrency(currency)
        // Get the result from the database
        val result = currencyDao.getCurrencyList().first()

        // <---------- ASSERT ---------->
        assertThat(currency.currencyKey).isEqualTo(result[0].currencyKey)
    }

    @Test
    fun addRates() {
        // <---------- ARRANGE ---------->
        // Prepare the list of currencies
        val noOfElementsWeInsert = 4
        // Add all the currency one by one to database
        currencyDao.apply {
            addRates(FakeRates.rates1)
            addRates(FakeRates.rates2)
            addRates(FakeRates.rates3)
            addRates(FakeRates.rates4)
        }

        // <---------- ACT ---------->
        val sizeOfRatesList = currencyDao.getCurrencyRatesList().size

        // <---------- ASSERT ---------->
        //Check the number of items inserted is equal to what we expect
        assertThat(sizeOfRatesList).isEqualTo(noOfElementsWeInsert)
    }

}