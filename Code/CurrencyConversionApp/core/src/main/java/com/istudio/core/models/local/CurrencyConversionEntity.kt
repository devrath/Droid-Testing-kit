package com.istudio.core.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "currency_conversion_table")
data class CurrencyConversionEntity(
    @PrimaryKey(autoGenerate = true)
    val slNo: Int = 0,
    @ColumnInfo(name = "disclaimer")
    val disclaimer: String? = null,
    @ColumnInfo(name = "license")
    val license: String? = null,
    @ColumnInfo(name = "timestamp")
    val timestamp: Int? = null,
    @ColumnInfo(name = "base")
    val base: String? = null,
)
