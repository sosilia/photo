package com.example.photo.data.source

import com.example.photo.data.source.local.entity.Bookmark
import com.example.photo.data.source.local.entity.BookmarkEntityMapper
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.repository.BookmarkRepository

class FakeBookmarkRepository : BookmarkRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    fun addBookmark(bookmark: BookmarkDto) {
        bookmarkList.add(bookmark)
    }

    private val bookmarkList = mutableListOf<BookmarkDto>()

    override suspend fun getBookmarkList(): List<BookmarkDto> {
        if (shouldReturnError) {
            throw Exception("Test exception")
        }

        return bookmarkList
    }

    override suspend fun getBookmarkList(page: Int, pageSize: Int): List<BookmarkDto> {
        if (shouldReturnError) {
            throw Exception("Test exception")
        }

        val size = bookmarkList.size
        val from = (page - 1) * pageSize
        val to = if (size > page * pageSize) page * pageSize else size
        return bookmarkList.subList(from, to)
    }

    override suspend fun setBookmark(bookmark: BookmarkDto) {
        if (shouldReturnError) {
            throw Exception("Test exception")
        }

        bookmarkList.add(bookmark)
    }

    override suspend fun removeBookmark(bookmarkId: String) {
        if (shouldReturnError) {
            throw Exception("Test exception")
        }

        bookmarkList.removeIf { it.id == bookmarkId }
    }
}