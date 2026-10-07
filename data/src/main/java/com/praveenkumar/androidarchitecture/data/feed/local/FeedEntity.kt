package com.praveenkumar.androidarchitecture.data.feed.local

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "feed_items")
data class FeedEntity(
    @PrimaryKey
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?
)
