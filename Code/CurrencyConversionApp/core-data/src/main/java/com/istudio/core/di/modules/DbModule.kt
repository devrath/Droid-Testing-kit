package com.istudio.core.di.modules

import com.istudio.core.data.implementation.CurrencyApiImpl
import com.istudio.core.data.implementation.CurrencyDbImpl
import com.istudio.core.data.modules.api.CurrencyApi
import com.istudio.core.data.modules.db.dao.CurrencyDao
import com.istudio.core.data.repository.CurrencyApiRepository
import com.istudio.core.data.repository.CurrencyDbRepository
import com.istudio.core.domain.features.CurrencyApiFeatures
import com.istudio.core.domain.features.CurrencyDbFeatures
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DbModule {

    @Provides
    @Singleton
    fun provideDbRepository(
        feature: CurrencyDbFeatures
    ) = CurrencyDbRepository(feature)

    @Provides
    @Singleton
    fun provideDbFeature(dao: CurrencyDao): CurrencyDbFeatures {
        return CurrencyDbImpl(dao)
    }

}