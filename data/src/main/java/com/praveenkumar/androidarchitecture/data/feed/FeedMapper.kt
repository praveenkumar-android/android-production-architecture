package com.praveenkumar.androidarchitecture.data.feed

import com.praveenkumar.androidarchitecture.data.feed.local.FeedEntity
import com.praveenkumar.androidarchitecture.domain.feed.model.FeedItem

fun FeedEntity.toDomain(): FeedItem {
    return FeedItem(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl
    )
}

fun FeedItem.toEntity(): FeedEntity{
    return FeedEntity(
        id = id,
        title = title,
        description = description,
        imageUrl = imageUrl
    )
}