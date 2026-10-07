package com.praveenkumar.androidproductionarchitecture.di


import com.praveenkumar.androidarchitecture.data.feed.repository.FeedRepositoryImpl
import com.praveenkumar.androidarchitecture.domain.feed.repository.FeedRepository
import com.praveenkumar.androidarchitecture.domain.feed.usecase.ObserveFeedUseCase
import com.praveenkumar.androidarchitecture.domain.feed.usecase.RefreshFeedUseCase
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class FeedModule {

    @Singleton
    @Binds
    abstract fun bindFeedRepository(
        feedRepositoryImpl: FeedRepositoryImpl,
    ): FeedRepository

    companion object {

        @Singleton
        @Provides
        fun provideObserveFeedUseCase(repository: FeedRepository): ObserveFeedUseCase {
            return ObserveFeedUseCase(repository)
        }

        @Singleton
        @Provides
        fun provideRefreshFeedUseCase(repository: FeedRepository): RefreshFeedUseCase {
            return RefreshFeedUseCase(repository)
        }
    }
}