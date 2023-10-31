package com.istudio.currency_converter.data.repository

import com.istudio.currency_converter.data.implementation.RepositoryControllerImpl
import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import com.istudio.currency_converter.domain.usecases.FeatureUseCases
import javax.inject.Inject

class RepositoryControllerFeatures @Inject constructor(
    private val repoImpl : CurrencyControllerFeatures
) : CurrencyControllerFeatures {
    override suspend fun fetchDataFromRepository() : String {
       return repoImpl.fetchDataFromRepository()
    }

    override suspend fun updateDataOnRepository() : String  {
        return repoImpl.updateDataOnRepository()
    }

    override suspend fun deleteDataOnRepository() : String  {
        return repoImpl.deleteDataOnRepository()
    }

}