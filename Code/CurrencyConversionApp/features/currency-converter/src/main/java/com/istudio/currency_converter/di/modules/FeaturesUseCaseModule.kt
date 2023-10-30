package com.istudio.currency_converter.di.modules

import com.istudio.core.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.CurrencyApiRepository
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetApiDataUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object FeaturesUseCaseModule {
    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        @IoDispatcher dispatcher: CoroutineDispatcher,
        api: CurrencyApiRepository
    ): FeatureUseCases {
        return FeatureUseCases(
            getApiDataUseCase = GetApiDataUseCase(dispatcher = dispatcher, api = api)
        )
    }
}