package com.example.photo.data.source

import com.example.photo.data.source.local.entity.Bookmark
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto

interface BookmarkDataSource {
    suspend fun removeBookmark(bookmarkId: String)

    suspend fun setBookmark(bookmark : Bookmark)

    suspend fun getBookmarkList() : List<Bookmark>

    suspend fun getBookmarkList(limit : Int, offSet : Int) : List<Bookmark>
}