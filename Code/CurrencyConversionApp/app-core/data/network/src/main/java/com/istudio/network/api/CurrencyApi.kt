package com.istudio.network.api

import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.network.BuildConfig
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import retrofit2.http.Url

interface CurrencyApi {

    companion object {
        const val APP_ID = BuildConfig.API_KEY
        const val DEFAULT_CURRENCY = "USD"
        const val DEFAULT_CURRENCY_NAME = "United States Dollar"
    }

    // API -> Getting the list of currencies
    // DOC -> https://docs.openexchangerates.org/reference/currencies-json
    @GET("currencies.json")
    suspend fun getCurrencies(): Currencies

    // API -> Getting the conversion values for all currencies
    // DOC -> https://docs.openexchangerates.org/reference/latest-json
    @GET("latest.json?app_id=$APP_ID")
    suspend fun getCurrencyConversionValues(
        @Query("base") base:String=DEFAULT_CURRENCY)
    : CurrencyConversionValues

}