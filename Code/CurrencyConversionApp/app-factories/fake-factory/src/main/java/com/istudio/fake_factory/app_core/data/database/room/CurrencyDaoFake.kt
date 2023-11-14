package com.istudio.fake_factory.app_core.data.database.room

import com.istudio.database.room.dao.CurrencyDao
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow

class CurrencyDaoFake : CurrencyDao {
    override fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        TODO("Not yet implemented")
    }

    override fun getCurrencyRatesList(): List<RatesEntity> {
        TODO("Not yet implemented")
    }

    override fun addCurrency(currency: CurrencyEntity) {
        TODO("Not yet implemented")
    }

    override fun addRates(currency: RatesEntity) {
        TODO("Not yet implemented")
    }

    override fun getCurrencyById(currencyKey: String): CurrencyEntity {
        TODO("Not yet implemented")
    }


}