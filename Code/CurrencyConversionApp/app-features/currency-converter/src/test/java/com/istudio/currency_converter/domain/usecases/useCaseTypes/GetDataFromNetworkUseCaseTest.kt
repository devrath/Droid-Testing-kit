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
class GetDataFromNetworkUseCaseTest {

    //SUT
    private lateinit var sut : GetDataFromNetworkUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        // Prepare a fake repository controller
        val repoControllerFake = RepositoryControllerFeatures(
            repoImpl = CurrencyControllerFeaturesFake()
        )
        sut = GetDataFromNetworkUseCase(
            dispatcher = testDispatcher, repoController = repoControllerFake
        )
    }

    @Test
    fun `Test data list is received successfully from server`() = runTest {
        // <---------- ARRANGE ---------->
        val input = "AED"
        val valueToTest = FakeApiKeyValuePairs.firstCurrencyValue

        // <---------- ACT ---------->
        val result = sut.invoke(input)

        result.map { flowOfResult ->
            val currencyNameFromDb = flowOfResult.currencies.AED

            // <---------- ASSERT ---------->
            //Check the number of items inserted is equal to what we expect
            assertThat(currencyNameFromDb).isEqualTo(valueToTest)
        }
    }


}