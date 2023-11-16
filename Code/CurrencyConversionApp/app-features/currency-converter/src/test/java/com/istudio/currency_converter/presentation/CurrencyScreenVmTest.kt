package com.istudio.currency_converter.presentation

import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import com.istudio.currency_converter.domain.usecases.useCaseTypes.CanUiBeDisplayedUseCase
import com.istudio.currency_converter.testUtils.MainCoroutineExtension
import kotlinx.coroutines.Dispatchers
import org.junit.jupiter.api.Assertions.*

import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.api.extension.ExtendWith


@ExtendWith(MainCoroutineExtension::class)
class CurrencyScreenVmTest {

    // SUT
    private lateinit var sut : CurrencyScreenVm
    private val testDispatcher = Dispatchers.Main

    @BeforeEach
    fun setUp() {
       /* sut = CurrencyScreenVm(
            mainDispatcher = testDispatcher,
            useCases = FeatureUseCases(

            )
        )*/
    }



}