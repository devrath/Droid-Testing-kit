package com.istudio.mock_factory.jsongenerators

import android.content.Context
import android.util.Log
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.istudio.models.local.CurrencyEntity
import java.io.IOException

object CurrencyJsonData {
    fun getCountryCode(context: Context): List<CurrencyEntity> {

        lateinit var jsonString: String
        try {
            jsonString = context.assets.open("serverMocks/Currencies.json")
                .bufferedReader()
                .use { it.readText() }
        } catch (ioException: IOException) {
            Log.d("Tag",ioException.stackTrace.toString())
        }

        val listCountryType = object : TypeToken<List<CurrencyEntity>>() {}.type
        return Gson().fromJson(jsonString, listCountryType)
    }
}