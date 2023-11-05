package com.istudio.database.room

import androidx.room.Database
import androidx.room.RoomDatabase
import com.istudio.database.Keys.DATABASE_VERSION
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyConversionEntity
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity


@Database(
    version = DATABASE_VERSION,
    entities = [
        // List of currencies
        CurrencyEntity::class,
        // List of rates
        RatesEntity::class,
        // List of <currencies + list> combines
        CurrencyConversionEntity::class
    ]
)
abstract class CurrencyDatabase : RoomDatabase() {
    abstract fun currencyDao(): CurrencyDao
}