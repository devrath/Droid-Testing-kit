package com.istudio.currency_converter.data.implementation

import com.istudio.currency_converter.domain.features.CurrencyControllerFeatures
import javax.inject.Inject

class RepositoryControllerImpl @Inject constructor(

) : CurrencyControllerFeatures {

    override suspend fun fetchDataFromRepository() :String {
        TODO("Our implementation to be written")
        return ""
    }

    override suspend fun updateDataOnRepository()  :String {
        TODO("Our implementation to be written")
        return ""
    }

    override suspend fun deleteDataOnRepository() :String {
        TODO("Our implementation to be written")
        return ""
    }

}