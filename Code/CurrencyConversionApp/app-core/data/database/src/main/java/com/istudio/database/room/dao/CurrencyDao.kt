package com.istudio.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

/**
 * DAO interface defines all the query operations to be performed on the database
 */
@Dao
interface CurrencyDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of currencies
     */
    @Query("SELECT * FROM currency_table")
    fun getCurrencyList() : Flow<List<CurrencyEntity>>

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of currency rates
     */
    @Query("SELECT * FROM rates_table")
    fun getCurrencyRatesList() : List<RatesEntity>

    /**
     * OPERATION: Inserting
     *
     * Adding a new currency to the CurrencyEntity table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addCurrency(currency : CurrencyEntity)

    /**
     * OPERATION: Inserting
     *
     * Adding a new rates to the RatesEntity table
     */
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun addRates(currency : RatesEntity)

    /**
     * OPERATION: Retrieving
     *
     * Getting a currency from database for a particular currency key
     */
    @Query("SELECT * FROM currency_table WHERE currencyKey=:currencyKey")
    fun getCurrencyById(currencyKey:String) : CurrencyEntity

}