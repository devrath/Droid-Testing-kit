package com.istudio.fake_factory.app_core.data.database.data.implementation

import com.istudio.database.domain.CurrencyDbFeatures
import com.istudio.database.room.dao.CurrencyDao
import com.istudio.fake_factory.app_core.data.database.domain.CurrencyDbFeaturesFake
import com.istudio.fake_factory.app_core.data.database.room.CurrencyDaoFake
import com.istudio.models.local.CurrencyAndRates
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import javax.inject.Inject

class CurrencyDbImplFake @Inject constructor(
    private val currencyDao : CurrencyDaoFake
) : CurrencyDbFeaturesFake() {


    override suspend fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        TODO("Not yet implemented")
    }


    override suspend fun addCurrency(currency: CurrencyEntity) {
        TODO("Not yet implemented")
    }


    override suspend fun addRates(rate: RatesEntity) {
        TODO("Not yet implemented")
    }


    override suspend fun getCurrencyAndRates(): List<CurrencyAndRates>{
        TODO("Not yet implemented")
    }

}