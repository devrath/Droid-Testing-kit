package com.istudio.currency_converter.domain.features

import com.istudio.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    suspend fun getBooks() : Flow<List<CurrencyEntity>>
}