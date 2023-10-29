package com.istudio.common_feature.di

import android.content.Context
import com.istudio.common_feature.data.usecases.CheckConnectivityUseCase
import com.istudio.common_feature.data.usecases.access.CommonFeaturesUseCases
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
        @ApplicationContext context: Context
    ): CommonFeaturesUseCases {
        return CommonFeaturesUseCases(
            connectivity = CheckConnectivityUseCase(context=context)
        )
    }


}