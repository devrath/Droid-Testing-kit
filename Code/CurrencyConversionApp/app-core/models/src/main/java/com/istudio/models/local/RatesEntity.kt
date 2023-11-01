package com.istudio.models.local

import androidx.annotation.NonNull
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "rates_table")
data class RatesEntity(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "ratesKey")
    val ratesKey : String,
    @ColumnInfo(name = "ratesValue")
    val ratesValue : Double? = null
)
