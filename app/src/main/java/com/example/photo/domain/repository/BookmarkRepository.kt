package com.example.photo.domain.repository

import com.example.photo.domain.model.BookmarkDto

interface BookmarkRepository {
    suspend fun getBookmarkList() : List<BookmarkDto>

    suspend fun getBookmarkList(page : Int, pageSize : Int) : List<BookmarkDto>

    suspend fun setBookmark(bookmark : BookmarkDto)

    suspend fun removeBookmark(bookmarkId : String)
}