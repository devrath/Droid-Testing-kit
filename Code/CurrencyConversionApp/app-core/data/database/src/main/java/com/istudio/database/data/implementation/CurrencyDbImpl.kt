package com.istudio.database.data.implementation

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    override suspend fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        return currencyDao.getCurrencyList()
    }

    override suspend fun addCurrency(currency: CurrencyEntity) {
        currencyDao.addCurrency(currency)
    }

    override suspend fun addRates(rate: RatesEntity) {
        currencyDao.addRates(rate)
    }

}