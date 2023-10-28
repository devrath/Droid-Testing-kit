package com.istudio.core.data.api

import com.istudio.core.BuildConfig
import com.istudio.core.data.models.Currencies
import com.istudio.core.data.models.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow
import retrofit2.http.GET

interface CurrencyApi {

    companion object {
        const val APP_ID = BuildConfig.API_KEY
    }

    // API -> Getting the list of currencies
    @GET("currencies.json")
    suspend fun getCurrencies(): Flow<Currencies>

    // API -> Getting the conversion values for all currencies
    @GET("latest.json?app_id=$APP_ID")
    suspend fun getCurrencyConversionValues(): Flow<CurrencyConversionValues>

}