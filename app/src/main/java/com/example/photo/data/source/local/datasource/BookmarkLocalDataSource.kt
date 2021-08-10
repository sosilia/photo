package com.example.photo.data.source.local.datasource

import com.example.photo.data.source.BookmarkDataSource
import com.example.photo.data.source.local.dao.BookmarkDao
import com.example.photo.data.source.local.entity.Bookmark
import com.example.photo.data.source.local.entity.BookmarkEntityMapper
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import javax.inject.Inject

class BookmarkLocalDataSource @Inject constructor(
    private val bookmarkDao: BookmarkDao,
) : BookmarkDataSource {

    override suspend fun removeBookmark(bookmarkId: String) {
        bookmarkDao.deleteBookmark(bookmarkId)
    }

    override suspend fun setBookmark(bookmark: Bookmark) {
        bookmarkDao.insertBookmark(bookmark)
    }

    override suspend fun getBookmarkList(): List<Bookmark> {
        return bookmarkDao.getAllBookmarks()
    }

    override suspend fun getBookmarkList(limit: Int, offSet: Int): List<Bookmark> {
        return bookmarkDao.getBookmarks(limit, offSet)
    }
}