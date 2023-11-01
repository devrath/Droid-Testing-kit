package com.istudio.database.room.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

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

}