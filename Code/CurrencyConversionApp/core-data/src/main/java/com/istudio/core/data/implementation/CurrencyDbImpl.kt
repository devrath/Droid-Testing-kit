package com.istudio.core.data.implementation

import com.istudio.core.data.modules.db.dao.CurrencyDao
import com.istudio.core.domain.features.CurrencyDbFeatures
import com.istudio.core.domain.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbImpl @Inject constructor(
    private val currencyDao : CurrencyDao
) : CurrencyDbFeatures {

    override suspend fun getBooks(): Flow<List<CurrencyEntity>> {
        return currencyDao.getBooks()
    }

}