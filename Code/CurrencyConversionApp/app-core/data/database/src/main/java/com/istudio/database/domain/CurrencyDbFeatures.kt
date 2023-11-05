package com.istudio.database.domain

import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of currencies
     */
    suspend fun getCurrencyList() : Flow<List<CurrencyEntity>>

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new currency value into the database for currency table
     */
    suspend fun addCurrency(currency: CurrencyEntity)

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new rate value into the database for the rates table
     */
    suspend fun addRates(rate: RatesEntity)

    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of rates and the currency matched to each rates combined
     */
    suspend fun getCurrencyAndRates() : List<CurrencyAndRates>
}