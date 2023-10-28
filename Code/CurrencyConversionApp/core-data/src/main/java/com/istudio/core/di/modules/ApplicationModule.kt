package com.istudio.core.di.modules

import com.istudio.network.BuildConfig
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import java.util.concurrent.TimeUnit
import javax.inject.Singleton

const val BASE_URL = "https://openexchangerates.org/api"

@Module
@InstallIn(SingletonComponent::class)
object ApplicationModule {

    @Provides
    fun provideBaseUrl() = BASE_URL

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


    @Provides
    @Singleton
    fun provideRetrofit(
        okHttpClient: OkHttpClient,
        BASE_URL: String
    ): Retrofit {
        //val contentType = "application/json".toMediaType()
        val retrofit = Retrofit.Builder()
            .baseUrl("https://example.com/")
            //.addConverterFactory(Json.asConverterFactory(contentType))
            .build()

        return retrofit
    }

}