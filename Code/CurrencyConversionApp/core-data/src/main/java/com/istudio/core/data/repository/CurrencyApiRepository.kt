package com.istudio.core.data.repository

import com.istudio.core.domain.api.CurrencyApiFeatures
import com.istudio.core.domain.models.Currencies
import com.istudio.core.domain.models.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyApiRepository @Inject constructor(
    private val features : CurrencyApiFeatures
) {

    suspend fun getCurrencies(): Flow<Currencies> {
        return features.getCurrencies()
    }

    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues> {
        return features.getCurrencyConversionValues()
    }

}