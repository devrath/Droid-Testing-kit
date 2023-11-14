package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isTrue
import com.google.gson.Gson
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.fake_factory.app_features.currency_converter.data.repository.RepositoryControllerFeaturesFake
import com.istudio.fake_factory.app_features.currency_converter.domain.features.CurrencyControllerFeaturesFake
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.custom.MasterApiData
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class InsertDataIntoDbUseCaseTest {

    // SUT
    private lateinit var sut : InsertDataIntoDbUseCase
    private val testDispatcher = Dispatchers.Main


    @BeforeEach
    fun setUp() {
        // Set the fake controller
        val repositoryControllerFeaturesFake = RepositoryControllerFeaturesFake(
            repoImpl = CurrencyControllerFeaturesFake()
        )
        // Set the SUT
        sut = InsertDataIntoDbUseCase(
            dispatcher = testDispatcher,
            gson = Gson(), repoController = repositoryControllerFeaturesFake
        )
    }

    @Test
    fun `test inserting the value into the DB successful scenario`() = runTest {
        // <---------- ARRANGE ---------->
        val mainDataSet = MasterApiData(
            currencies = FakeApiData.getFakeApiCurriencies(),
            conversionValues = FakeApiData.getFakeApiCurriencyConversionValues()
        )

        // <---------- ACT ---------->
        val result = sut.invoke(mainDataSet)

        // <---------- ASSERT ---------->
        assertThat(result.isSuccess).isTrue()

    }


}