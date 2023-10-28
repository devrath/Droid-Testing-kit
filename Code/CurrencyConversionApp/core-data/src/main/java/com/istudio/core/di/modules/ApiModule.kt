package com.istudio.core.di.modules

import com.istudio.core.data.implementation.CurrencyApiImpl
import com.istudio.core.data.modules.api.CurrencyApi
import com.istudio.core.data.repository.CurrencyApiRepository
import com.istudio.core.domain.api.CurrencyApiFeatures
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object ApiModule {

    @Provides
    @Singleton
    fun provideFirebaseAuthRepository(
        feature: CurrencyApiFeatures
    ) = CurrencyApiRepository(feature)

    @Provides
    @Singleton
    fun provideFirebaseAuthFeature(api: CurrencyApi): CurrencyApiFeatures {
        return CurrencyApiImpl(api)
    }

}