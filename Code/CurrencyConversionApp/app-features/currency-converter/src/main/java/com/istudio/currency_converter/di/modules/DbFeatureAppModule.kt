package com.istudio.currency_converter.di.modules

import com.istudio.core.modules.db.dao.CurrencyDao
import com.istudio.currency_converter.data.implementation.CurrencyDbImpl
import com.istudio.currency_converter.data.repository.CurrencyDbRepository
import com.istudio.currency_converter.domain.features.CurrencyDbFeatures
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

/**
 * We inject Repository wherever needed
 * As a good practice:->  Instead of Currency DB interface we use the repository
 */
@Module
@InstallIn(SingletonComponent::class)
object DbFeatureAppModule {


    /*****************************
     * PROVIDES:-> Currency DB repository Implementation
     * ***************************
     * CONSTRUCTOR INPUT:-> Currency DB Implementation
     *****************************/
    @Provides
    @Singleton
    fun provideDbRepository(features: CurrencyDbImpl): CurrencyDbRepository {
        return CurrencyDbRepository(features)
    }

    /*****************************
     * PROVIDES:-> Currency DB Implementation
     * ***************************
     * CONSTRUCTOR INPUT:-> Currency DAO - Interface
     *****************************/
    @Provides
    @Singleton
    fun provideApiImpl(dao: CurrencyDao): CurrencyDbFeatures {
        return CurrencyDbImpl(dao)
    }



}