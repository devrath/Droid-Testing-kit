package com.istudio.currency_converter.domain.features

interface CurrencyControllerFeatures {
    suspend fun fetchDataFromRepository() : String
    suspend fun updateDataOnRepository()  : String
    suspend fun deleteDataOnRepository() : String
}