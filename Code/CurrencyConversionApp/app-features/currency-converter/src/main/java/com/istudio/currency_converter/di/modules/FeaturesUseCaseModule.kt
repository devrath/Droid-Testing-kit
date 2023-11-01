package com.istudio.currency_converter.di.modules

import com.google.gson.Gson
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetDataFromNetworkUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.InsertDataIntoDbUseCase
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
        repo: RepositoryControllerFeatures,
        gson: Gson
    ): FeatureUseCases {
        return FeatureUseCases(
            network = GetDataFromNetworkUseCase(
                dispatcher = dispatcher,
                repoController = repo
            ),
            database = InsertDataIntoDbUseCase(
                dispatcher = dispatcher,
                repoController = repo,
                gson = gson
            )
        )
    }
}