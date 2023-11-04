package com.istudio.models.local

import androidx.annotation.NonNull
import androidx.compose.runtime.mutableStateOf
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.Ignore
import androidx.room.PrimaryKey
import com.istudio.models.Keys.defaultCurrency

@Entity(tableName = "currency_table")
data class CurrencyEntity(
    @PrimaryKey @NonNull
    @ColumnInfo(name = "currencyKey")
    val currencyKey : String,
    @ColumnInfo(name = "currencyName")
    val currencyName : String? = null
){
    @Ignore
    var isItemSelected = mutableStateOf(false)

    private fun setDefault() {
        isItemSelected.value = (currencyKey==defaultCurrency.currencyKey)
    }
}
