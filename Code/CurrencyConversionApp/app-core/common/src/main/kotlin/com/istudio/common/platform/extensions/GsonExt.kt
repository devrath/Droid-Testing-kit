package com.istudio.common.platform.extensions

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

//convert a data class to a map
fun <T> T.serializeToMap(gson: Gson): Map<String, Any> {
    return convert(gson)
}

//convert a map to a data class
inline fun <reified T> Map<String, Any>.toDataClass(gson: Gson): T {
    return convert(gson)
}

//convert an object of type I to type O
inline fun <I, reified O> I.convert(gson: Gson): O {
    val json = gson.toJson(this)
    return gson.fromJson(json, object : TypeToken<O>() {}.type)
}