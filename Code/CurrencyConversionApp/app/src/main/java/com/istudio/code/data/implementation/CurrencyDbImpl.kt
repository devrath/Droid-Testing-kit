package com.istudio.code.data.implementation

import com.istudio.code.domain.features.CurrencyDbFeatures
import com.istudio.core.models.local.CurrencyEntity
import com.istudio.core.modules.db.dao.CurrencyDao
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    override suspend fun getBooks(): Flow<List<CurrencyEntity>> {
        return currencyDao.getBooks()
    }

}