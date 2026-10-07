package com.praveenkumar.androidarchitecture.feature.home

import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem

data class HomeUiState (
    val isLoading: Boolean = false,
    val feed: List<FeedItem> = emptyList(),
    val errorMessage: String? = null
)

sealed interface HomeUiEvent {
    data object Load: HomeUiEvent
    data object Refresh: HomeUiEvent
}

sealed interface HomeUiEffect {
    data class ShowError(val message: String? = null): HomeUiEffect
}