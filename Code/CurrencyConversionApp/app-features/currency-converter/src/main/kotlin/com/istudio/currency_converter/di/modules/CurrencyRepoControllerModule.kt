package com.istudio.currency_converter.di.modules

import com.istudio.currency_converter.data.implementation.RepositoryControllerImpl
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.database.data.repository.CurrencyDbRepository
import com.istudio.network.data.CurrencyApiRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import dagger.hilt.android.scopes.ViewModelScoped


@Module
@InstallIn(ViewModelComponent::class)
object CurrencyRepoControllerModule {

    @ViewModelScoped
    @Provides
    fun provideRepositoryFeatures(
        repo : CurrencyControllerFeatures
    ) : RepositoryControllerFeatures {
        return RepositoryControllerFeatures(repo)
    }


    @ViewModelScoped
    @Provides
    fun provideRepositoryController(
         api : CurrencyApiRepository, db : CurrencyDbRepository
    ) : CurrencyControllerFeatures {
        return RepositoryControllerImpl(api=api,db=db)
    }

}