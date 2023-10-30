package com.istudio.common_feature.di

import android.content.Context
import android.net.ConnectivityManager
import com.istudio.common_feature.domain.usecases.useCaseMain.CheckConnectivityUseCase
import com.istudio.common_feature.domain.usecases.useCaseTypes.CommonFeaturesUseCases
import com.istudio.core.platform.extensions.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.android.scopes.ViewModelScoped
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonFeatureAppModule {

    @Singleton
    @Provides
    fun providesConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.connectivityManager
    }

}