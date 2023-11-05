package com.istudio.database.data.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import app.cash.turbine.test
import com.google.common.truth.Truth.assertThat
import com.istudio.database.generators.currencyDummy
import com.istudio.database.room.CurrencyDatabase
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.database.testUtils.MainCoroutineExtension
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.count
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.take
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach

import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

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
    fun getCurrencyList() = runTest {
        // <---------- ARRANGE ---------->
        // Prepare the list of currencies
        val noOfElementsWeInsert = 4
        val currency1 = currencyDummy().copy(currencyKey = "USD",currencyName = "United states dollar")
        val currency2 = currencyDummy().copy(currencyKey = "AED",currencyName = "United Arab Emirates Dirham")
        val currency3 = currencyDummy().copy(currencyKey = "AFN",currencyName = "Afghan Afghani")
        val currency4 = currencyDummy().copy(currencyKey = "ALL",currencyName = "Albanian Lek")

        // <---------- ACT ---------->
        // Add all the currency one by one to database
        currencyDao.apply {
            addCurrency(currency1)
            addCurrency(currency2)
            addCurrency(currency3)
            addCurrency(currency4)
        }
        // Get the result from the database
        val sut = currencyDao.getCurrencyList()
        sut.test {
            // <---------- ASSERT ---------->
            assertThat(sut.count()).isEqualTo(noOfElementsWeInsert)
        }



    }

    @Test
    fun addCurrency() = runTest {
        // <---------- ARRANGE ---------->
        // Prepare the currency row
        val currency = currencyDummy().copy(currencyKey = "USD",currencyName = "United states dollar")

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
    }

    @Test
    fun getCurrencyAndRates() {
    }
}