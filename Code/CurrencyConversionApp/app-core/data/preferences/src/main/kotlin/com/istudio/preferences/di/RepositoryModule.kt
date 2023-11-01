package com.istudio.preferences.di

import android.content.Context
import com.istudio.preferences.data.RepositoryPreferences
import com.istudio.preferences.domain.PreferencesFeature
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RepositoryModule {

    @Provides
    @Singleton
    fun provideRepositoryDatastore(
        @ApplicationContext context: Context,
        store: PreferencesFeature
    ) = RepositoryPreferences(appDatastore = store)

}