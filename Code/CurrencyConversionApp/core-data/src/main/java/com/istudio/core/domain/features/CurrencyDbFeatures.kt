package com.istudio.core.domain.features

import com.istudio.core.domain.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    suspend fun getBooks() : Flow<List<CurrencyEntity>>
}