package com.istudio.database.di

import com.istudio.database.data.implementation.CurrencyDbImpl
import com.istudio.database.data.repository.CurrencyDbRepository
import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.database.room.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbFeatureAppModule {

    // PROVIDES:-> Currency Db repository Implementation
    @Provides
    @Singleton
    fun provideDbRepository(features: CurrencyDbFeatures): CurrencyDbRepository {
        return CurrencyDbRepository(features)
    }

    // PROVIDES:-> Currency Db Implementation
    @Provides
    @Singleton
    fun provideDbImpl(dao: CurrencyDao): CurrencyDbFeatures {
        return CurrencyDbImpl(dao)
    }

}