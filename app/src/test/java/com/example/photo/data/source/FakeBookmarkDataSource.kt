package com.example.photo.data.source

import com.example.photo.data.source.BookmarkDataSource
import com.example.photo.data.source.local.entity.Bookmark

class FakeBookmarkDataSource(var bookmarkList: MutableList<Bookmark>? = mutableListOf()) :
    BookmarkDataSource {

    override suspend fun removeBookmark(bookmarkId: String) {
        bookmarkList?.removeIf{ it.id == bookmarkId}
    }

    override suspend fun setBookmark(bookmark: Bookmark) {
        bookmarkList?.add(bookmark)
    }

    override suspend fun getBookmarkList(): List<Bookmark> {
        return bookmarkList ?: emptyList()
    }

    override suspend fun getBookmarkList(limit: Int, offset: Int): List<Bookmark> {
        return bookmarkList?.subList(offset, limit) ?: emptyList()
    }
}