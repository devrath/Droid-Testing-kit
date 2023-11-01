package com.istudio.preferences.domain

interface PreferencesFeature {
    suspend fun saveTimestamp(text: String)
    suspend fun loadTimestamp() : String?

    suspend fun saveIsDataSaved(isSaved: Boolean)
    suspend fun loadIsDataSaved() : Boolean?
}