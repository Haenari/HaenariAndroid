package com.haenari.haenari.data.datastore

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.preferencesDataStore

/**
 * File Name
 */
const val SAMPLE_PREFERENCES_NAME = "sample_preferences"

object PreferencesKey {
    val SAMPLE_STRING = "sample_string"
}

/***
 * preferences DataStore
 */
val Context.samplePreferencesDataStore: DataStore<Preferences> by preferencesDataStore(name = SAMPLE_PREFERENCES_NAME)