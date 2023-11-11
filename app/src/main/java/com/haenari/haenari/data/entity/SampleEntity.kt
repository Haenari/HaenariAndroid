package com.haenari.haenari.data.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.haenari.haenari.data.database.BaseDatabase

@Entity(
    tableName = BaseDatabase.SAMPLE_TABLE
)
data class SampleEntity (
    @PrimaryKey val name: String
)