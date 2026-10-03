package com.praveenkumar.androidarchitecture.data.feed.repository

import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem
import com.praveenkumar.androidarchitecture.domain.feed.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor() : FeedRepository {
    private val feed = MutableStateFlow(
        listOf(
            FeedItem(
                id = "1",
                title = "Building Production Android Apps",
                description = "Architecture, modularization and maintainability.",
                imageUrl = null
            ),
            FeedItem(
                id = "2",
                title = "Offline-First Android",
                description = "Designing applications that work reliably without a network.",
                imageUrl = null
            )
        )
    )

    override fun observeFeed(): Flow<List<FeedItem>> {
        return feed.asStateFlow()
    }

    override suspend fun refreshFeed() {
        // Network + local database will be implemented later.
    }
}