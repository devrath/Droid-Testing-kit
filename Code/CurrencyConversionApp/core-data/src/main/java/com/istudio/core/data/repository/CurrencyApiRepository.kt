package com.istudio.core.data.repository

import com.istudio.core.domain.features.CurrencyApiFeatures
import com.istudio.core.domain.models.remote.Currencies
import com.istudio.core.domain.models.remote.CurrencyConversionValues
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