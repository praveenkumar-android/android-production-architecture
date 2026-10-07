package com.praveenkumar.androidarchitecture.data.feed.repository

import com.praveenkumar.androidarchitecture.data.feed.local.FeedDao
import com.praveenkumar.androidarchitecture.data.feed.toDomain
import com.praveenkumar.androidarchitecture.data.feed.toEntity
import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem
import com.praveenkumar.androidarchitecture.domain.feed.repository.FeedRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject

class FeedRepositoryImpl @Inject constructor(
    private val feedDao: FeedDao
) : FeedRepository {

    override fun observeFeed(): Flow<List<FeedItem>> {
        return feedDao.observeFeed()
            .map { entities ->
                entities.map { entity -> entity.toDomain() }
            }
    }

    override suspend fun refreshFeed() {
        val feedItems = listOf(
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

        feedDao.upsertAll(
            feedItems.map { it.toEntity() }
        )
    }
}