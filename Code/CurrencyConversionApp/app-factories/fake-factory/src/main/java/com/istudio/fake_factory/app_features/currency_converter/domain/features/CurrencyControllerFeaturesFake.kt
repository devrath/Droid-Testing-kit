package com.istudio.fake_factory.app_features.currency_converter.domain.features

import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class CurrencyControllerFeaturesFake : CurrencyControllerFeatures {

    val listOfCurrencyEntity: ArrayList<CurrencyEntity> = arrayListOf()
    val listOfRatesEntity: ArrayList<RatesEntity> = arrayListOf()
    val listOfCurrencyAndRatesEntity: ArrayList<CurrencyAndRates> = arrayListOf()


    override suspend fun getCurrenciesFromApi(): Currencies {
        // <------ Get values from MOCKED response ------------>
        return FakeApiData.getFakeApiCurriencies()
        // <------ Get values from MOCKED response ------------>
    }

    override suspend fun getCurrencyConversionValuesFromApi(base: String): CurrencyConversionValues {
        // <------ Get values from MOCKED response ------------>
        return FakeApiData.getFakeApiCurriencyConversionValues()
        // <------ Get values from MOCKED response ------------>
    }

    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates> {
        // <------ Add values to DB fake ------------>
        // performBatchTransactionOfData()
        // <------ Add values to DB fake ------------>
        return listOfCurrencyAndRatesEntity
    }

    override suspend fun insertCurrenciesIntoDb(currency: CurrencyEntity) {
        // <------ Add values to DB fake ------------>
        //addCurrencyEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
    }

    override suspend fun insertRatesIntoDb(rates: RatesEntity) {
        // <------ Add values to DB fake ------------>
        //addRatesEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
    }

    override suspend fun getCurrenciesListFromDb(): Flow<List<CurrencyEntity>> = flow {
        // <------ Add values to DB fake ------------>
        //addCurrencyEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
        emit(listOfCurrencyEntity)
    }

    override suspend fun getRatesListFromDb(): List<CurrencyAndRates> {
        // <------ Add values to DB fake ------------>
        //performBatchTransactionOfData()
        // <------ Add values to DB fake ------------>
        return listOfCurrencyAndRatesEntity
    }

}