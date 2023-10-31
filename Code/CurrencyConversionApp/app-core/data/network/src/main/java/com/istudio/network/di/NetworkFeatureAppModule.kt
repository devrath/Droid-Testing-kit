package com.istudio.network.di

import com.istudio.network.api.CurrencyApi
import com.istudio.network.data.CurrencyApiImpl
import com.istudio.network.data.CurrencyApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * We inject Repository wherever needed
 * As a good practice:-> Instead of Currency API interface we use the repository
 */
@Module
@InstallIn(SingletonComponent::class)
object NetworkFeatureAppModule {

    /*****************************
     * PROVIDES:-> Currency API repository Implementation
     * ***************************
     * CONSTRUCTOR INPUT:-> Currency API Implementation
     *****************************/
    @Provides
    @Singleton
    fun provideApiRepository(features: CurrencyApiImpl): CurrencyApiRepository {
        return CurrencyApiRepository(features)
    }

    /*****************************
     * PROVIDES:-> Currency api Implementation
     * ***************************
     * CONSTRUCTOR INPUT:-> Currency API - Interface
     *****************************/
    @Provides
    @Singleton
    fun provideApiImpl(api: CurrencyApi): com.istudio.network.domain.CurrencyApiFeatures {
        return CurrencyApiImpl(api)
    }

}