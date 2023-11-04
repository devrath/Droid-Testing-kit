package com.istudio.currency_converter.domain.features

import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow

interface CurrencyControllerFeatures {

    // <------------------------------------ API  <------------------------------------>
    // <----- Retrieving Data  ----->
    suspend fun getCurrenciesFromApi() : Currencies
    suspend fun getCurrencyConversionValuesFromApi(base:String)  : CurrencyConversionValues
    // <----- Retrieving Data  ----->
    // <------------------------------------ API  <------------------------------------>



    // <------------------------------------ DB  <------------------------------------>
    // <----- Inserting Data  ----->
    suspend fun insertCurrenciesIntoDb(currency : CurrencyEntity)
    suspend fun insertRatesIntoDb(rates : RatesEntity)
    // <----- Inserting Data  ----->

    // <----- Retrieving Data  ---->
    suspend fun getCurrenciesListFromDb() : Flow<List<CurrencyEntity>>
    suspend fun getRatesListFromDb() : Flow<List<RatesEntity>>
    // <----- Retrieving Data  ---->
    // <------------------------------------ DB  <------------------------------------>



}