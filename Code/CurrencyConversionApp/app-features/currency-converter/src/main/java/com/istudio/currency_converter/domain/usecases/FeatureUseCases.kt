package com.istudio.currency_converter.domain.usecases

import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyListDataFromDb
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetCurrencyRatesListDataFromDb
import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetDataFromNetworkUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.InsertDataIntoDbUseCase

data class FeatureUseCases(
    // Use-Case:-> Network
    val network : GetDataFromNetworkUseCase,
    // Use-Case:-> Database
    val dbInsertAllData: InsertDataIntoDbUseCase,
    // Use-Case:-> Get Currencies data from DB
    val dbRetrieveCurrencies: GetCurrencyListDataFromDb,
    // Use-Case:-> Get Currency rates data from DB
    val dbRetrieveCurrencyRates: GetCurrencyRatesListDataFromDb,
)
