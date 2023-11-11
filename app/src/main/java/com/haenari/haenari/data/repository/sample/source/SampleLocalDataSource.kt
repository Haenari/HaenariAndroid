package com.haenari.haenari.data.repository.sample.source

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import com.haenari.haenari.data.dao.SampleDao

class SampleLocalDataSource(
    private val dataStore: DataStore<Preferences>,
    private val dao: SampleDao
) {

}