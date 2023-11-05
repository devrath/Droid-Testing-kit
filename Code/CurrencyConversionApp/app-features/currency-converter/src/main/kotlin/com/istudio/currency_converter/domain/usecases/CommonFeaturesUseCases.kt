package com.istudio.currency_converter.domain.usecases

import com.istudio.currency_converter.domain.usecases.useCaseTypes.CheckConnectivityUseCase
import com.istudio.currency_converter.domain.usecases.useCaseTypes.IsNewDataToBeFetchedFromServerUseCase

data class CommonFeaturesUseCases(
    // Use-Case:-> Check Connectivity
    val connectivity: CheckConnectivityUseCase,
    // Use-Case:-> Determine if new data has to be fetched from server
    val IsNewDataToBeFetchedFromServerUseCase : IsNewDataToBeFetchedFromServerUseCase
)
