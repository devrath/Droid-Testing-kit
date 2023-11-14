package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.google.gson.Gson
import com.istudio.common.platform.extensions.serializeToMap
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.fake_factory.app_features.currency_converter.domain.features.CurrencyControllerFeaturesFake
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import com.istudio.models.remote.Currencies
import com.istudio.models.remote.Rates
import io.mockk.coEvery
import io.mockk.every
import io.mockk.mockk
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class GetCurrencyRatesListDataFromDbUseCaseTest {

    //SUT
    private lateinit var sut : GetCurrencyRatesListDataFromDbUseCase
    private val testDispatcher = Dispatchers.Main

    @Test
    fun `Test CurrencyAndRates list is received successfully from database`() = runTest {
        // <---------- ARRANGE ---------->
        // Gson data
        val gson = Gson()
        // Currency fake data
        val curriencies: Currencies = FakeApiData.getFakeApiCurriencies()
        // Rates fake data
        val rates: Rates = FakeApiData.getFakeApiCurriencyConversionValues().rates

        val currencyControllerFeature = CurrencyControllerFeaturesFake()

        // Add the fake currency data to fake currency controller
        curriencies.serializeToMap(gson).mapValues { currencyMap ->
            val key: String = currencyMap.key
            val value: String = currencyMap.value as String
            currencyControllerFeature.insertCurrenciesIntoDb(
                CurrencyEntity(currencyKey = key,currencyName = value)
            )
        }
        // Add the fake rates data to fake currency controller
        rates.serializeToMap(gson).mapValues { ratesMap ->

            val key: String = ratesMap.key
            val value: Double = ratesMap.value as Double

            currencyControllerFeature.insertRatesIntoDb(
                RatesEntity(ratesKey = key,ratesValue = value)
            )
        }

        val repoControllerFake = RepositoryControllerFeatures(repoImpl = currencyControllerFeature)
        sut = GetCurrencyRatesListDataFromDbUseCase(dispatcher = testDispatcher, repoController = repoControllerFake)

        val input = Unit
        val valueToTest = FakeApiKeyValuePairs.firstCurrencyValue

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map { flowOfResult ->
            val currencyNameFromDb = flowOfResult.first().currency.currencyName

            //Check the number of items inserted is equal to what we expect
            assertThat(currencyNameFromDb).isEqualTo(valueToTest)
        }
    }


}