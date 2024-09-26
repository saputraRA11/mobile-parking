package com.example.parking.data.preferences

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.flow.Flow

val Context.dataStore:DataStore<Preferences> by preferencesDataStore(name = "settings")
class SettingLocalStorage private constructor(private val dataStore: DataStore<Preferences>) {
    fun getSetting(key:String):Flow<String> {
        val keyStore = stringPreferencesKey(key)
        return dataStore.data.map { preferences ->
            preferences[keyStore] ?: ""
        }
    }

    suspend fun saveSetting(key: String, value:String) {
        val keyStore = stringPreferencesKey(key)
        dataStore.edit { preferences ->
            preferences[keyStore] = value
        }
    }

    suspend fun clearSettings(key: String) {
        val keyStore = stringPreferencesKey(key)
        dataStore.edit {
            if(it.contains(keyStore)){
                it.remove(keyStore)
            }
        }
    }

    suspend fun clearSettings() {
        dataStore.edit {
           it.clear()
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: SettingLocalStorage? = null

        fun getInstance(dataStore: DataStore<Preferences>): SettingLocalStorage {
            return INSTANCE ?: synchronized(this) {
                val instance = SettingLocalStorage(dataStore)
                INSTANCE = instance
                instance
            }
        }
    }
}