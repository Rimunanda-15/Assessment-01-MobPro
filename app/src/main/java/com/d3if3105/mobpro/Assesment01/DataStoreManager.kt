// DataStoreManager.kt
package com.d3if3105.mobpro.Assesment01

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.first

object DataStoreManager {
    private const val PREFERENCES_NAME = "bangun_datar_preferences"

    private val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = PREFERENCES_NAME)

    // Definisikan keys yang akan digunakan
    private object Keys {
        val LAST_USED_SHAPE = stringPreferencesKey("last_used_shape")
    }

    // Fungsi untuk menyimpan bentuk terakhir yang digunakan
    suspend fun saveLastUsedShape(context: Context, shape: String) {
        context.dataStore.edit { preferences ->
            preferences[Keys.LAST_USED_SHAPE] = shape
        }
    }

    // Fungsi untuk mengambil bentuk terakhir yang digunakan
    suspend fun getLastUsedShape(context: Context): String? {
        val preferences = context.dataStore.data.first()
        return preferences[Keys.LAST_USED_SHAPE]
    }
}
