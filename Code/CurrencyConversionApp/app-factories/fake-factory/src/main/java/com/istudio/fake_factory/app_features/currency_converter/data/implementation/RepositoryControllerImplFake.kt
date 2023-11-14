package com.istudio.fake_factory.app_features.currency_converter.data.implementation

import com.istudio.fake_factory.app_features.currency_converter.domain.features.CurrencyControllerFeaturesFake
import com.istudio.network.data.CurrencyApiRepository

class RepositoryControllerImplFake(
    // Network access
    private val api : CurrencyApiRepository,
    // Database access
    //private val db : CurrencyDbRepositoryFake
) : CurrencyControllerFeaturesFake() {



}