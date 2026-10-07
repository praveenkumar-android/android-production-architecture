package com.praveenkumar.androidarchitecture.data.feed.local

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(
    entities = [FeedEntity::class],
    version = 1,
    exportSchema = true
)
abstract class AppDatabase: RoomDatabase() {
    abstract fun feedDao(): FeedDao
}