package com.istudio.currency_converter.di.modules

import android.net.ConnectivityManager
import com.istudio.common.platform.coroutines.dispatcher.IoDispatcher
import com.istudio.common.platform.coroutines.dispatcher.MainDispatcher
import com.istudio.currency_converter.domain.usecases.CommonFeaturesUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CheckConnectivityUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.IsNewDataToBeFetchedFromServerUseCase
import com.istudio.preferences.data.RepositoryPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped
import kotlinx.coroutines.CoroutineDispatcher

@Module
@InstallIn(ViewModelComponent::class)
object CommonFeatureModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        @IoDispatcher dispatcher : CoroutineDispatcher,
        connectivityManager : ConnectivityManager,
        preferences : RepositoryPreferences
    ): CommonFeaturesUseCases {
        return CommonFeaturesUseCases(
            connectivity = CheckConnectivityUseCase(
                connectivityManager=connectivityManager
            ),
            IsNewDataToBeFetchedFromServerUseCase = IsNewDataToBeFetchedFromServerUseCase(
                dispatcher = dispatcher, preferences = preferences
            )
        )
    }


}