package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isEqualTo
import com.istudio.currency_converter.data.repository.RepositoryControllerFeatures
import com.istudio.currency_converter.fakes.domain.features.CurrencyControllerFeaturesFake
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.mock_factory.generators.FakeApiKeyValuePairs
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class GetCurrencyListDataFromDbUseCaseTest {

    //SUT
    private lateinit var sut : GetCurrencyListDataFromDbUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        // Prepare a fake repository controller
        val repoControllerFake = RepositoryControllerFeatures(
            repoImpl = CurrencyControllerFeaturesFake()
        )
        sut = GetCurrencyListDataFromDbUseCase(
            dispatcher = testDispatcher, repoController = repoControllerFake
        )
    }

    @Test
    fun `Test currency list is recieved successfully from database`() = runTest {
        // <---------- ARRANGE ---------->
        val input = Unit
        val valueToTest = FakeApiKeyValuePairs.firstCurrencyValue

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        // <---------- ASSERT ---------->
        result.map { flowOfResult ->
            flowOfResult.map { listOfResult ->
                val currencyNameFromDb = listOfResult.first().currencyName
                //Check the number of items inserted is equal to what we expect
                assertThat(currencyNameFromDb).isEqualTo(valueToTest)
            }
        }
    }


}