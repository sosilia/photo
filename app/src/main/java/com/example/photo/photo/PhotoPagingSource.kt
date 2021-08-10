package com.example.photo.photo

import androidx.paging.PagingSource
import androidx.paging.PagingState
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.usecase.GetPhotoUseCase
import javax.inject.Inject

class PhotoPagingSource @Inject constructor(
    private val getPhotosUseCase: GetPhotoUseCase
) : PagingSource<Int, PhotoDto>() {

    override fun getRefreshKey(state: PagingState<Int, PhotoDto>): Int? {
        return null
    }

    override suspend fun load(params: LoadParams<Int>): LoadResult<Int, PhotoDto> {
        // Start refresh at page 1 if undefined.
        val nextPageNumber = params.key ?: 1
        val response = getPhotosUseCase(GetPhotoUseCase.GetPhotoParam(nextPageNumber))
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

}