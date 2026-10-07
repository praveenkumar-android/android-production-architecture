package com.praveenkumar.androidarchitecture.data.di

import android.content.Context
import androidx.room.Room
import com.praveenkumar.androidarchitecture.data.feed.local.AppDatabase
import com.praveenkumar.androidarchitecture.data.feed.local.FeedDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule {

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "production_architecture.db"
        ).build()
    }

    @Provides
    fun provideFeedDao(
        database: AppDatabase
    ): FeedDao {
        return database.feedDao()
    }
}