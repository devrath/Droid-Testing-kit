package com.istudio.database.data.implementation

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import com.istudio.database.room.dao.CurrencyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    override suspend fun getBooks(): Flow<List<CurrencyEntity>> {
        return currencyDao.getBooks()
    }

}