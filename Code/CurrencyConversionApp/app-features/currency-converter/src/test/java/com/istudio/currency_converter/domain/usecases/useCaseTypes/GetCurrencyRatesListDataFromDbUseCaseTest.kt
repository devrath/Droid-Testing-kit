package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.fake_factory.app_features.currency_converter.domain.features.CurrencyControllerFeaturesFake
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
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

    @BeforeEach
    fun setUp() {
        // Prepare a fake repository controller
        val repoControllerFake = RepositoryControllerFeatures(
            repoImpl = CurrencyControllerFeaturesFake()
        )
        sut = GetCurrencyRatesListDataFromDbUseCase(
            dispatcher = testDispatcher, repoController = repoControllerFake
        )
    }

    @Test
    fun `Test CurrencyAndRates list is received successfully from database`() = runTest {
        // <---------- ARRANGE ---------->
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