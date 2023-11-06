package com.istudio.currency_converter.fakes.data.implementation

import com.istudio.currency_converter.fakes.domain.features.CurrencyControllerFeaturesFake
import com.istudio.database.data.repository.CurrencyDbRepository
import com.istudio.network.data.CurrencyApiRepository

class RepositoryControllerImplFake(
    // Network access
    private val api : CurrencyApiRepository,
    // Database access
    private val db : CurrencyDbRepository
) : CurrencyControllerFeaturesFake() {



}