package com.istudio.common.di.modules

import android.content.Context
import androidx.room.Room
import com.istudio.common.modules.db.CurrencyDatabase
import com.istudio.common.modules.db.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    @Singleton
    @Provides
    fun provideBookDao(appDatabase: CurrencyDatabase): CurrencyDao {
        return appDatabase.currencyDao()
    }

    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            appContext, CurrencyDatabase::class.java, CurrencyDatabase.DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

}