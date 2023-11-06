package com.istudio.currency_converter.fakes.domain.features

import androidx.compose.runtime.mutableStateOf
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
import com.nhaarman.mockitokotlin2.mock
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.test.StandardTestDispatcher
import kotlinx.coroutines.test.TestDispatcher

open class CurrencyControllerFeaturesFake : CurrencyControllerFeatures {

    val listOfCurrencyEntity : ArrayList<CurrencyEntity> = arrayListOf()
    val listOfRatesEntity : ArrayList<RatesEntity> = arrayListOf()
    val listOfCurrencyAndRatesEntity : ArrayList<CurrencyAndRates> = arrayListOf()


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
        performBatchTransactionOfData()
        // <------ Add values to DB fake ------------>
        return listOfCurrencyAndRatesEntity
    }

    override suspend fun insertCurrenciesIntoDb(currency: CurrencyEntity) {
        // <------ Add values to DB fake ------------>
        addCurrencyEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
    }

    override suspend fun insertRatesIntoDb(rates: RatesEntity) {
        // <------ Add values to DB fake ------------>
        addRatesEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
    }

    override suspend fun getCurrenciesListFromDb(): Flow<List<CurrencyEntity>> = flow {
        // <------ Add values to DB fake ------------>
        addCurrencyEntityDataToMockedCollection()
        // <------ Add values to DB fake ------------>
        emit(listOfCurrencyEntity)
    }

    override suspend fun getRatesListFromDb(): List<CurrencyAndRates> {
        // <------ Add values to DB fake ------------>
        performBatchTransactionOfData()
        // <------ Add values to DB fake ------------>
        return listOfCurrencyAndRatesEntity
    }


    /**
     * Adding the data to local collection of list of currencyEntities
     */
    private fun addCurrencyEntityDataToMockedCollection() {
        val currencyDataFromApi = FakeApiData.getFakeApiCurriencies()

        val curriencies: Currencies = currencyDataFromApi
        val currienciesMapped: Map<String, Any> = curriencies.serializeToMap(Gson())

        currienciesMapped.mapValues { currencyMap ->
            val key: String = currencyMap.key
            val value: String = currencyMap.value as String

            println("$key $value")

            listOfCurrencyEntity.add(CurrencyEntity(currencyKey = key, currencyName = value))
        }
    }

    /**
     * Adding the data to local collection of list of ratesEntities
     */
    private fun addRatesEntityDataToMockedCollection() {
        val currencyDataFromApi = FakeApiData.getFakeApiCurriencyConversionValues()
        val rates: Rates = currencyDataFromApi.rates
        val ratesMapped: Map<String, Any> = rates.serializeToMap(Gson())

        ratesMapped.mapValues { ratesMap ->

            val key: String = ratesMap.key
            val value: Double = ratesMap.value as Double

            println("$key $value")

            listOfRatesEntity.add(
                RatesEntity(ratesKey = key, ratesValue = value)
            )
        }
    }

    /**
     * Get matched currency entity
     */
    private fun getCurrencyEntity(ratesEntity: RatesEntity): CurrencyEntity? {
        listOfCurrencyEntity.forEachIndexed { index, currencyEntity ->
            if (ratesEntity.ratesKey == currencyEntity.currencyKey) {
                return currencyEntity
            }
        }
        return null
    }

    /**
     * Get CurrencyRates from DB
     */
    private fun getCurrencyRatesFromDb() {
        // Reset values
        listOfCurrencyAndRatesEntity.clear()
        // Get new values from mocked data
        listOfRatesEntity.forEachIndexed { index, ratesEntity ->
            getCurrencyEntity(ratesEntity)?.let {
                listOfCurrencyAndRatesEntity.add(
                    CurrencyAndRates(rates = ratesEntity, currency = it)
                )
            }
        }
    }

    /**
     * Perform batch transaction of data
     */
    private suspend fun performBatchTransactionOfData() {


        // <------ Add values to DB fake ------------>
        addCurrencyEntityDataToMockedCollection()
        addRatesEntityDataToMockedCollection()
        getCurrencyRatesFromDb()
        // <------ Add values to DB fake ------------>
    }
}