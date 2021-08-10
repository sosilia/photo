package com.example.photo.di

import com.example.photo.data.repository.BookmarkRepositoryImpl
import com.example.photo.data.repository.PhotoRepositoryImpl
import com.example.photo.data.source.BookmarkDataSource
import com.example.photo.data.source.local.datasource.BookmarkLocalDataSource
import com.example.photo.domain.repository.BookmarkRepository
import com.example.photo.domain.repository.PhotoRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@InstallIn(SingletonComponent::class)
@Module
abstract class RepositoryModule {
    @Binds
    abstract fun bindPhotoRepository(photoRepositoryImpl: PhotoRepositoryImpl) : PhotoRepository

    @Binds
    abstract fun bindBookmarkRepository(bookmarkRepositoryImpl: BookmarkRepositoryImpl) : BookmarkRepository
}