package com.istudio.preferences.data

import com.istudio.preferences.domain.PreferencesFeature
import javax.inject.Inject

class RepositoryPreferences @Inject constructor(
    val appDatastore: PreferencesFeature
) {

    suspend fun saveTimeStamp(textToSave: String) {
        appDatastore.saveTimestamp(textToSave)
    }

    suspend fun getTimeStamp(): String? {
        return appDatastore.loadTimestamp()
    }

    suspend fun saveIsDataSaved(isDataSaved: Boolean) {
        appDatastore.saveIsDataSaved(isDataSaved)
    }

    suspend fun getIsDataSaved(): Boolean? {
        return appDatastore.loadIsDataSaved()
    }

}