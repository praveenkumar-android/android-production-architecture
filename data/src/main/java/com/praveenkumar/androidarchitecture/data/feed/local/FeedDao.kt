package com.praveenkumar.androidarchitecture.data.feed.local

import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface FeedDao {

    @Query("SELECT * FROM feed_items")
    fun observeFeed(): Flow<List<FeedEntity>>

    @Upsert
    suspend fun upsertAll(items: List<FeedEntity>)

    @Query("DELETE FROM feed_items")
    suspend fun clearAll()
}