package com.istudio.currency_converter.data.repository

import com.istudio.code.domain.features.CurrencyDbFeatures
import com.istudio.core.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbRepository @Inject constructor(
    private val features : CurrencyDbFeatures
) {

    suspend fun getCurrencies(): Flow<List<CurrencyEntity>> {
        return features.getBooks()
    }

}