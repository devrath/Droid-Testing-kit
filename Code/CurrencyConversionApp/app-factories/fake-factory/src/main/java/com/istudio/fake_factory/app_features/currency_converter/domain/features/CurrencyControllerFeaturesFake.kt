package com.istudio.fake_factory.app_features.currency_converter.domain.features

import com.google.gson.Gson
import com.istudio.common.platform.extensions.serializeToMap
import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.CurrencyConversionValues
import com.istudio.models.remote.Rates
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

open class CurrencyControllerFeaturesFake : CurrencyControllerFeatures {

    private val listOfCurrencyEntity: ArrayList<CurrencyEntity> = arrayListOf()
    private val listOfRatesEntity: ArrayList<RatesEntity> = arrayListOf()
    private val listOfCurrencyAndRatesEntity: ArrayList<CurrencyAndRates> = arrayListOf()


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
        performBatchTransactionOfData(listOfCurrencyEntity,listOfRatesEntity)
        // <------ Add values to DB fake ------------>
        return listOfCurrencyAndRatesEntity
    }

    override suspend fun insertCurrenciesIntoDb(currency: CurrencyEntity) {
        // <------ Add values to DB fake ------------>
        val key: String = currency.currencyKey
        val value: String = currency.currencyName as String

        listOfCurrencyEntity.add(CurrencyEntity(currencyKey = key, currencyName = value))
        // <------ Add values to DB fake ------------>
    }

    override suspend fun insertRatesIntoDb(rates: RatesEntity) {
        // <------ Add values to DB fake ------------>
        val key: String = rates.ratesKey
        val value: Double = rates.ratesValue as Double

        listOfRatesEntity.add(RatesEntity(ratesKey = key, ratesValue = value))
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



    private fun performBatchTransactionOfData(
        listOfCurrencyEntity: ArrayList<CurrencyEntity>,
        listOfRatesEntity: ArrayList<RatesEntity>
    ) {
        listOfCurrencyEntity.forEachIndexed{ index, item ->
            listOfCurrencyAndRatesEntity.add(
                CurrencyAndRates(
                    currency = item, rates = listOfRatesEntity[index]
                )
            )
        }
    }

}