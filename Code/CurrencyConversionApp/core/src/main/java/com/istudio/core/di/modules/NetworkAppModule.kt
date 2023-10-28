package com.istudio.core.di.modules

import com.istudio.core.BuildConfig
import com.istudio.core.modules.network.api.CurrencyApi
import com.jakewharton.retrofit2.converter.kotlinx.serialization.asConverterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.serialization.json.Json
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://openexchangerates.org/api"

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    /*****************************
     * PROVIDES:-> Currency-Api
     * ***************************
     * CONSTRUCTOR INPUT:-> Retrofit
     *****************************/
    @Provides
    @Singleton
    fun provideCurrencyApi(
        retrofit: Retrofit
    ): CurrencyApi {
        return retrofit.create(CurrencyApi::class.java)
    }

    /*****************************
     * PROVIDES:-> Retrofit
     * ***************************
     * CONSTRUCTOR INPUT:-> OkHttp
     *****************************/
    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient
    ): Retrofit {
        val contentType = "application/json".toMediaType()
        return Retrofit.Builder()
            .baseUrl("https://openexchangerates.org/api")
            .client(okHttpClient)
            .addConverterFactory(Json.asConverterFactory(contentType))
            .build()
    }

    /*****************************
     * PROVIDES:-> Okhttp
     * ***************************
     * CONSTRUCTOR INPUT:-> None
     *****************************/
    @Provides
    @Singleton
    fun provideOkHttpClient(): OkHttpClient {
        val okHttpBuilder = OkHttpClient.Builder()
        if (BuildConfig.DEBUG) {
            // Add interceptor only for DEBUG
            val loggingInterceptor = HttpLoggingInterceptor()
            loggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY)
            okHttpBuilder.addInterceptor(loggingInterceptor)
        }
        // Add the timeouts
        okHttpBuilder.apply {
            connectTimeout(60, TimeUnit.SECONDS)
            writeTimeout(60, TimeUnit.SECONDS)
            readTimeout(60, TimeUnit.SECONDS)
        }
        // Construct the object
        return okHttpBuilder.build()
    }

}