package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
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