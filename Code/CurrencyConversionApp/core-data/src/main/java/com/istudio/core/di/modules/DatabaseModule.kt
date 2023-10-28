package com.istudio.core.di.modules

import com.istudio.core.data.modules.db.CurrencyDatabase
import com.istudio.core.data.modules.db.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Singleton
    @Provides
    fun provideCurrencyDao(appDatabase: CurrencyDatabase): CurrencyDao {
        return appDatabase.currencyDao()
    }


}