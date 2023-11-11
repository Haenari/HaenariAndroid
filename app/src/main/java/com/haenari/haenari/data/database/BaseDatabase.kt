package com.haenari.haenari.data.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.haenari.haenari.data.dao.SampleDao
import com.haenari.haenari.data.entity.SampleEntity

@Database(
    version = 1,
    exportSchema = false,
    entities = [SampleEntity::class]
)
abstract class BaseDatabase : RoomDatabase() {
    companion object {
        const val SAMPLE_TABLE: String = "sample_table"
    }

    abstract fun sampleDao(): SampleDao
}