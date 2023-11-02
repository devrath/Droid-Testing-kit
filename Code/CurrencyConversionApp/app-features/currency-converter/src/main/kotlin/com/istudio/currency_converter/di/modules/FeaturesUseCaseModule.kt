package com.istudio.currency_converter.di.modules

import com.google.gson.Gson
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyListDataFromDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyRatesListDataFromDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetDataFromNetworkUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.InsertDataIntoDbUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.IsNewDataToBeFetchedFromServerUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.SaveTimeStampUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.SetRateGridSelectionUseCase
import com.istudio.preferences.data.RepositoryPreferences
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
        preferences : RepositoryPreferences,
        gson: Gson,
    ): FeatureUseCases {
        return FeatureUseCases(
            network = GetDataFromNetworkUseCase(
                dispatcher = dispatcher, repoController = repo
            ),
            dbInsertAllData = InsertDataIntoDbUseCase(
                dispatcher = dispatcher, repoController = repo, gson = gson
            ),
            dbRetrieveCurrencies = GetCurrencyListDataFromDbUseCase(
                dispatcher = dispatcher, repoController = repo
            ),
            dbRetrieveCurrencyRates = GetCurrencyRatesListDataFromDbUseCase(
                dispatcher = dispatcher, repoController = repo
            ),
            canUiBeDisplayedUseCase = CanUiBeDisplayedUseCase(
                dispatcher = dispatcher
            ),
            saveTimeStampUseCase = SaveTimeStampUseCase(
                dispatcher = dispatcher, preferences = preferences
            ),
            isNewDataToBeFetchedFromServerUseCase = IsNewDataToBeFetchedFromServerUseCase(
                dispatcher = dispatcher, preferences = preferences
            ),
            setRateGridSelectionUseCase = SetRateGridSelectionUseCase(
                dispatcher = dispatcher
            )
        )
    }
}