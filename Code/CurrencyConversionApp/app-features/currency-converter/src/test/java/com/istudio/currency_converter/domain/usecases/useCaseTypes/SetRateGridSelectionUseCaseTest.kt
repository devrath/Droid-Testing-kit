package com.istudio.currency_converter.domain.usecases.useCaseTypes

import assertk.assertThat
import assertk.assertions.isTrue
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import com.istudio.models.custom.GridSelectionInput
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.test.runTest
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.Test
import org.junit.jupiter.api.extension.ExtendWith

@ExtendWith(MainCoroutineExtension::class)
class SetRateGridSelectionUseCaseTest {

    //SUT
    private lateinit var sut : SetRateGridSelectionUseCase
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
        sut = SetRateGridSelectionUseCase(dispatcher = testDispatcher)
    }

    @Test
    fun `Check if the selection is made if no elements are present in list of rates`() = runTest {
        // <---------- ARRANGE ---------->
        val input = GridSelectionInput(data = listOf(), selectedPosition = 0)

        // <---------- ACT ---------->
        val result = sut.invoke(input)


        result.map { res ->
            // <---------- ASSERT ---------->
            assertThat(res.isEmpty()).isTrue()
        }

    }

}