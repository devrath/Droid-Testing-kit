package com.istudio.fake_factory.app_core.data.database.room

import com.istudio.database.room.dao.CurrencyDao
import com.istudio.mock_factory.generators.FakeApiData
import com.istudio.models.local.CurrencyEntity
import com.istudio.models.local.RatesEntity
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf

class CurrencyDaoFake : CurrencyDao {

    private var ratesList : ArrayList<RatesEntity> = arrayListOf()
    private var currencyList : ArrayList<CurrencyEntity> = arrayListOf()

    override fun getCurrencyList(): Flow<List<CurrencyEntity>> {
        return flowOf(currencyList)
    }

    override fun getCurrencyRatesList(): List<RatesEntity> {
        return ratesList
    }

    override fun addCurrency(currency: CurrencyEntity) {
        currencyList.add(currency)
    }

    override fun addRates(rates: RatesEntity) {
        ratesList.add(rates)
    }

    override fun getCurrencyById(currencyKey: String): CurrencyEntity {
        for (currencyEntity in currencyList) {
            if(currencyEntity.currencyKey==currencyKey){
                // Return the correct value
                return currencyEntity
            }
        }
        // Just return some random value
        return currencyList[0]
    }


}