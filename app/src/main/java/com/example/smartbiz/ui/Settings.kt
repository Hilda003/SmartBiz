package com.example.smartbiz.ui

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit

import androidx.datastore.preferences.core.intPreferencesKey
import kotlinx.coroutines.flow.map

class Settings private constructor(private val dataStore: DataStore<Preferences>) {
    private val themeApp = intPreferencesKey("theme_app")


    companion object {
        @Volatile
        private var Instance: Settings? = null

        fun getInstance(dataStore: DataStore<Preferences>): Settings {
            return Instance ?: synchronized(this) {
                val instance = Settings(dataStore)
                Instance = instance
                instance

            }
        }
    }

    fun getThemeApp() = dataStore.data.map { preferences ->
        preferences[themeApp] ?: 0
    }


    suspend fun saveThemeApp(theme: Int) {
        dataStore.edit {
            it[themeApp] = theme
        }
    }



}