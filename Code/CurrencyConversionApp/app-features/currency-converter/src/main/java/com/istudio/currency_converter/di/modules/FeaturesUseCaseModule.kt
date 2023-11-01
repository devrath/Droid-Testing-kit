package com.istudio.currency_converter.di.modules

import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetApiDataUseCase
import com.istudio.network.data.CurrencyApiRepository
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
        repo: RepositoryControllerFeatures
    ): FeatureUseCases {
        return FeatureUseCases(
            getApiDataUseCase = GetApiDataUseCase(dispatcher = dispatcher, repoController = repo)
        )
    }
}