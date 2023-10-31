package com.istudio.common.di.modules

import android.content.Context
import android.net.ConnectivityManager
import com.istudio.common.platform.extensions.connectivityManager
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object CommonAppFeaturesModule {

    @Singleton
    @Provides
    fun providesConnectivityManager(
        @ApplicationContext context: Context
    ): ConnectivityManager {
        return context.connectivityManager
    }

}