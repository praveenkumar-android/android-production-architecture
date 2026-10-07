package com.praveenkumar.androidarchitecture.domain.feed.usecase

import com.praveenkumar.androidarchitecture.domain.feed.repository.FeedRepository

class RefreshFeedUseCase(
    private val repository: FeedRepository
) {
    suspend operator fun invoke() {
        repository.refreshFeed()
    }
}