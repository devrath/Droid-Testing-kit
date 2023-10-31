package com.istudio.common.modules.db.dao

import androidx.room.Dao
import androidx.room.Query
import com.istudio.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface CurrencyDao {

    /**
     * OPERATION: Retrieving
     *
     * Getting just a list of currencies
     */
    @Query("SELECT * FROM currency_table")
    fun getBooks() : Flow<List<CurrencyEntity>>

}