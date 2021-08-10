package com.example.photo.data.repository

import com.example.photo.data.source.BookmarkDataSource
import com.example.photo.data.source.local.entity.BookmarkEntityMapper
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.repository.BookmarkRepository
import javax.inject.Inject

class BookmarkRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkDataSource,
    private val bookmarkEntityMapper: BookmarkEntityMapper,
) : BookmarkRepository {
    override suspend fun getBookmarkList(): List<BookmarkDto> {
        return localDataSource.getBookmarkList().map {
            bookmarkEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun getBookmarkList(page: Int, pageSize: Int): List<BookmarkDto> {
        return localDataSource.getBookmarkList(pageSize, (page - 1) * pageSize).map {
            bookmarkEntityMapper.mapToDomain(it)
        }
    }

    override suspend fun setBookmark(bookmarkDto: BookmarkDto) {
        localDataSource.setBookmark(bookmarkEntityMapper.mapToEntity(bookmarkDto))
    }

    override suspend fun removeBookmark(bookmarkId: String) {
        localDataSource.removeBookmark(bookmarkId)
    }
}