package com.istudio.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.istudio.database.room.CurrencyDatabase.Companion.DATABASE_VERSION
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyConversionEntity
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity


@Database(
    entities = [
        CurrencyConversionEntity::class, CurrencyEntity::class, RatesEntity::class
    ],
    version = DATABASE_VERSION
)
abstract class CurrencyDatabase : RoomDatabase() {
    companion object {
        const val DATABASE_NAME = "CurrencyConverterDatabase"
        const val DATABASE_VERSION = 1

    }
    abstract fun currencyDao(): CurrencyDao
}