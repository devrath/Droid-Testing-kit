package com.istudio.core.data.modules.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.istudio.core.data.modules.db.CurrencyDatabase.Companion.DATABASE_VERSION
import com.istudio.core.data.modules.db.dao.CurrencyDao
import com.istudio.core.domain.models.local.CurrencyConversionEntity
import com.istudio.core.domain.models.local.CurrencyEntity
import com.istudio.core.domain.models.local.RatesEntity


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