package com.istudio.database.di

import android.content.Context
import androidx.room.Room
import com.istudio.database.Keys.DATABASE_NAME
import com.istudio.database.room.CurrencyDatabase
import com.istudio.database.room.dao.CurrencyDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseAppModule {

    // PROVIDES:-> Currency DAO from the database
    @Singleton
    @Provides
    fun provideBookDao(appDatabase: CurrencyDatabase): CurrencyDao {
        return appDatabase.currencyDao()
    }

    // PROVIDES:-> Currency database from the room functionality
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext appContext: Context): CurrencyDatabase {
        return Room.databaseBuilder(
            appContext, CurrencyDatabase::class.java, DATABASE_NAME
        ).allowMainThreadQueries().build()
    }

}