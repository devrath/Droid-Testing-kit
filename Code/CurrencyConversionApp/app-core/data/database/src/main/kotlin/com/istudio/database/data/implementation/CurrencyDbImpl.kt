package com.istudio.database.data.implementation

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

/**
 * ******************************************************************************************
 * Data-Source : DB repository implementation of the DAO
 * **********************************
 * Description : Getting the data from the local database
 * **********************************
 * How it is used : This class is the implementation of the DAO and a additional wrapper also acts as a way to combine multiple DAO's and return the data
 * ******************************************************************************************
 */
internal class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of currencies
     */
    override suspend fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        return currencyDao.getCurrencyList()
    }

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new currency value into the database for currency table
     */
    override suspend fun addCurrency(currency: CurrencyEntity) {
        currencyDao.addCurrency(currency)
    }

    /**
     * OPERATION : Adding
     *
     * RETURNS : Adding a new rate value into the database for the rates table
     */
    override suspend fun addRates(rate: RatesEntity) {
        currencyDao.addRates(rate)
    }

    /**
     * OPERATION : Retrieving
     *
     * RETURNS : List of rates and the currency matched to each rates combined
     */
    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates>{
        return currencyDao.getCurrencyRatesList().map {
            CurrencyAndRates(
                rates = it,
                currency = currencyDao.getCurrencyById(it.ratesKey)
            )
        }
    }

}