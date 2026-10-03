package com.praveenkumar.androidarchitecture.domain.feed.model

data class FeedItem(
    val id: String,
    val title: String,
    val description: String,
    val imageUrl: String?
)