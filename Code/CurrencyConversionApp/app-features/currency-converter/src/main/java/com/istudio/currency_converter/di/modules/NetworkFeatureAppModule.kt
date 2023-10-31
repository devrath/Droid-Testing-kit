package com.istudio.currency_converter.di.modules

import com.istudio.common.modules.network.api.CurrencyApi
import com.istudio.currency_converter.data.implementation.CurrencyApiImpl
import com.istudio.currency_converter.data.repository.CurrencyApiRepository
import com.istudio.currency_converter.domain.features.CurrencyApiFeatures
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
    fun provideApiImpl(api: com.istudio.common.modules.network.api.CurrencyApi): CurrencyApiFeatures {
        return CurrencyApiImpl(api)
    }

}