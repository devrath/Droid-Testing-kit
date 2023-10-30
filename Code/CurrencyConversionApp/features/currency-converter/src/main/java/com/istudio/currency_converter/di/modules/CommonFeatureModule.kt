package com.istudio.currency_converter.di.modules

import android.net.ConnectivityManager
import com.istudio.currency_converter.domain.usecases.CommonFeaturesUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CheckConnectivityUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped

@Module
@InstallIn(ViewModelComponent::class)
object CommonFeatureModule {

    @ViewModelScoped
    @Provides
    fun provideTrackerUseCases(
        connectivityManager : ConnectivityManager
    ): CommonFeaturesUseCases {
        return CommonFeaturesUseCases(
            connectivity = CheckConnectivityUseCase(connectivityManager=connectivityManager)
        )
    }


}