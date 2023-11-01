package com.istudio.preferences.data

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import androidx.datastore.preferences.core.stringPreferencesKey
import com.istudio.preferences.Keys.KEY_IS_DATA_SAVED
import com.istudio.preferences.Keys.KEY_TIMESTAMP
import com.istudio.preferences.domain.PreferencesFeature
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class PreferencesFeatureImpl @Inject constructor(
    private val dataStore: DataStore<Preferences>
): PreferencesFeature {

    companion object {
        private val KEY_TIME = stringPreferencesKey(KEY_TIMESTAMP)
        private val KEY_IS_DATA_STORED = booleanPreferencesKey(KEY_IS_DATA_SAVED)
    }

    override suspend fun saveTimestamp(text: String) {
        dataStore.edit { it[KEY_TIME] = text }
    }

    override suspend fun loadTimestamp(): String? {
        return dataStore.getValueFlow(KEY_TIME, "").first()
    }

    override suspend fun saveIsDataSaved(isSaved: Boolean) {
        dataStore.edit { it[KEY_IS_DATA_STORED] = isSaved }
    }

    override suspend fun loadIsDataSaved(): Boolean? {
        return dataStore.getValueFlow(KEY_IS_DATA_STORED, false).first()
    }

    private fun <T> DataStore<Preferences>.getValueFlow(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> {
        return this.data
            .catch { exception ->
                if (exception is IOException) { emit(emptyPreferences()) } else { throw exception }
            }.map { preferences ->
                preferences[key] ?: defaultValue
            }
    }

}