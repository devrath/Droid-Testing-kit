package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyApiFeatures
import com.istudio.core.models.remote.Currencies
import com.istudio.core.models.remote.CurrencyConversionValues
import com.istudio.core.modules.network.api.CurrencyApi
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import javax.inject.Inject

class CurrencyApiImpl @Inject constructor(
    private val api : CurrencyApi
) : CurrencyApiFeatures {
    override suspend fun getCurrencies(): Flow<Currencies> {
        return flowOf(api.getCurrencies())
    }

    override suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues> {
        return flowOf(api.getCurrencyConversionValues())
    }
}