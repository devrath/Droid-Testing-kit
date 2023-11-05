package com.istudio.database.data.implementation

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.istudio.database.room.CurrencyDatabase
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.runBlocking
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.AfterEach
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import java.util.concurrent.CountDownLatch

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
    fun getCurrencyList() {
    }

    @Test
    fun addCurrency() = runBlocking {

        // Prepare the currency row
        val currency = CurrencyEntity(currencyKey = "USD",currencyName = "United states dollar")
        // Add the currency row to the database using the dao
        currencyDao.addCurrency(currency)

        // Wait for one one second
      /*  val latch = CountDownLatch(1)

        val job = async(Dispatchers.IO) {
            wordsDao.getAllWords().collect {
                assertThat(it).contains(name)
                latch.countDown()

            }
        }
        latch.await()
        job.cancelAndJoin()*/
    }

    @Test
    fun addRates() {
    }

    @Test
    fun getCurrencyAndRates() {
    }
}