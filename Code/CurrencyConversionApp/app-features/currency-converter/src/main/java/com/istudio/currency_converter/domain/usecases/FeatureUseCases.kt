package com.istudio.currency_converter.domain.usecases

import com.istudio.currency_converter.domain.usecases.useCaseTypes.GetDataFromNetworkUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.InsertDataIntoDbUseCase

data class FeatureUseCases(
    // Use-Case:-> Network
    val network : GetDataFromNetworkUseCase,
    // Use-Case:-> Database
    val database: InsertDataIntoDbUseCase,
)
