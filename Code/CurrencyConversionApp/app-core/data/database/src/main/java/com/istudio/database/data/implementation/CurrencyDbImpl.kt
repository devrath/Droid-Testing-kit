package com.istudio.database.data.implementation

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.flow.flatMapMerge
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    override suspend fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        return currencyDao.getCurrencyList()
    }

    override suspend fun getCurrencyRatesList(): List<RatesEntity> {
        return currencyDao.getCurrencyRatesList()
    }

    override suspend fun addCurrency(currency: CurrencyEntity) {
        currencyDao.addCurrency(currency)
    }

    override suspend fun addRates(rate: RatesEntity) {
        currencyDao.addRates(rate)
    }

    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates>{
        return currencyDao.getCurrencyRatesList().map {
            CurrencyAndRates(
                rates = it,
                currency = currencyDao.getCurrencyById(it.ratesKey)
            )
        }
    }

}