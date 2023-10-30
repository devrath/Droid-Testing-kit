package com.istudio.currency_converter.domain.usecases

import com.istudio.currency_converter.domain.usecases.useCaseTypes.CheckConnectivityUseCase

data class CommonFeaturesUseCases(
    // Use-Case:-> Check Connectivity
    val connectivity: CheckConnectivityUseCase,
)
