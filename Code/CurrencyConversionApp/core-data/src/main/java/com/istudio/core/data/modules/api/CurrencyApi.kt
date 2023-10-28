package com.istudio.core.data.modules.api

import com.istudio.core.BuildConfig
import com.istudio.core.domain.models.remote.Currencies
import com.istudio.core.domain.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CurrencyApi {

    companion object {
        const val APP_ID = BuildConfig.API_KEY
    }

    // API -> Getting the list of currencies
    // DOC -> https://docs.openexchangerates.org/reference/currencies-json
    @GET("currencies.json")
    suspend fun getCurrencies(): Flow<Currencies>

    // API -> Getting the conversion values for all currencies
    // DOC -> https://docs.openexchangerates.org/reference/latest-json
    @GET("latest.json?app_id=$APP_ID")
    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues>

}