package com.istudio.common_feature.di

import android.content.Context
import android.net.ConnectivityManager
import com.istudio.common_feature.domain.usecases.useCaseMain.CheckConnectivityUseCase
import com.istudio.common_feature.domain.usecases.useCaseTypes.CommonFeaturesUseCases
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
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