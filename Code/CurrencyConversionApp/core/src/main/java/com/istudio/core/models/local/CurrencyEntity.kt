package com.istudio.core.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey(autoGenerate = true)
    val url: Int = 0,
    @ColumnInfo(name = "currencyKey")
    val currencyKey : String? = null,
    @ColumnInfo(name = "currencyName")
    val currencyName : String? = null
)
