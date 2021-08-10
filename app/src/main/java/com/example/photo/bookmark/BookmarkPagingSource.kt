package com.example.photo.bookmark

import android.util.Log
import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.usecase.GetBookmarkUseCase
import javax.inject.Inject

class BookmarkPagingSource @Inject constructor(
    private val getBookmarkUseCase: GetBookmarkUseCase
) : PagingSource<Int, BookmarkDto>() {

    override fun getRefreshKey(state: PagingState<Int, BookmarkDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, BookmarkDto> {
        // Start refresh at page 1 if undefined.
        Log.e("load", (params.key ?: 1).toString())
        val nextPageNumber = params.key ?: 1
        val response = getBookmarkUseCase(GetBookmarkUseCase.GetBookmarkParam(nextPageNumber, pageSize))
        val body = response.getOrDefault(emptyList())
        return if (response.isSuccess && body.isNotEmpty()) {
            LoadResult.Page(
                data = body,
                prevKey = null, // Only paging forward.
                nextKey = nextPageNumber + 1
            )
        } else {
            LoadResult.Page(
                data = emptyList(),
                prevKey = null, // Only paging forward.
                nextKey = null
            )
        }
    }

    companion object {
        private const val pageSize : Int = 10
    }

}