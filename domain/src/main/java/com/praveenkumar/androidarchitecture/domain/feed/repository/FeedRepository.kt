package com.praveenkumar.androidarchitecture.domain.feed.repository

import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem
import kotlinx.coroutines.flow.Flow

interface FeedRepository {

    fun observeFeed(): Flow<List<FeedItem>>

    suspend fun refreshFeed()
}