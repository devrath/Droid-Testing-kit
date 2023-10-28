package com.istudio.code.domain.features

import com.istudio.core.models.local.CurrencyEntity
import kotlinx.coroutines.flow.Flow

interface CurrencyDbFeatures {
    suspend fun getBooks() : Flow<List<CurrencyEntity>>
}