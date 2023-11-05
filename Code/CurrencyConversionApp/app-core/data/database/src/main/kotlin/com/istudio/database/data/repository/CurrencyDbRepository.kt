package com.istudio.database.data.repository

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ******************************************************************************************
 * Data-Source : DB repository
 * **********************************
 * Description : Getting the data from the local database
 * **********************************
 * How it is used : This is the class the clients use to access the database functionalities
 * ******************************************************************************************
 */
class CurrencyDbRepository @Inject constructor(
    private val features : CurrencyDbFeatures
) {
    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of currencies
     */
    suspend fun getCurrencies(): Flow<List<CurrencyEntity>> {
        return features.getCurrencyList()
    }

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new currency value into the database for currency table
     */
    suspend fun addCurrency(currency: CurrencyEntity) {
        features.addCurrency(currency)
    }

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new rate value into the database for the rates table
     */
    suspend fun addRates(currency: RatesEntity) {
        features.addRates(currency)
    }

    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of rates and the currency matched to each rates combined
     */
    suspend fun getCurrencyAndRates(): List<CurrencyAndRates> {
        return features.getCurrencyAndRates()
    }

}