package com.istudio.database.data.repository

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbRepository @Inject constructor(
    private val features : CurrencyDbFeatures
) {

    suspend fun getCurrencies(): Flow<List<CurrencyEntity>> {
        return features.getBooks()
    }

}