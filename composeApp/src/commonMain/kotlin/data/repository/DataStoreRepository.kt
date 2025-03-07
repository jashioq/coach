package data.repository

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.intPreferencesKey
import domain.repository.DataStoreRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.distinctUntilChanged
import kotlinx.coroutines.flow.map

class DataStoreRepository(
    private val dataStore: DataStore<Preferences>,
) : DataStoreRepository {
    override suspend fun putIntPreference(key: String, value: Int): Result<Unit> {
        val dataStoreKey = intPreferencesKey(key)
        return runCatching {
            dataStore.edit { preferences ->
                preferences[dataStoreKey] = value
            }
        }
    }

    override suspend fun emitIntPreference(key: String, default: Int): Result<Flow<Int>> {
        val dataStoreKey = intPreferencesKey(key)
        return runCatching {
            dataStore.data
                .map { preferences -> preferences[dataStoreKey] ?: default }
                .distinctUntilChanged()
        }
    }
}
