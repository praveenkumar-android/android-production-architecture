package com.praveenkumar.androidarchitecture.domain.feed.usecase

import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem
import com.praveenkumar.androidarchitecture.domain.feed.repository.FeedRepository
import kotlinx.coroutines.flow.Flow

class ObserveFeedUseCase(
    private val repository: FeedRepository
) {
    operator fun invoke(): Flow<List<FeedItem>> {
        return repository.observeFeed()
    }
}