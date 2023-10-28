package com.istudio.core.models.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_table")
data class RatesEntity(
    @PrimaryKey(autoGenerate = true)
    val url: Int = 0,
    @ColumnInfo(name = "ratesKey")
    val ratesKey : String? = null,
    @ColumnInfo(name = "ratesValue")
    val ratesValue : Double? = null
)
