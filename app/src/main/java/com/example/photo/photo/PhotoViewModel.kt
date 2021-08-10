package com.example.photo.photo

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.usecase.RemoveBookmarkUseCase
import com.example.photo.domain.usecase.SetPhotoBookmarkUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class PhotoViewModel @Inject constructor(
    private val photoPagingSource: PhotoPagingSource,
    private val setPhotoBookmarkUseCase: SetPhotoBookmarkUseCase,
    private val removeBookmarkUseCase: RemoveBookmarkUseCase,
) : ViewModel() {

    val flow = Pager(PagingConfig(pageSize = 10)) {
        photoPagingSource
    }.flow.cachedIn(viewModelScope)

    fun setBookMark(photoDto: PhotoDto, onSuccess: (() -> Unit)? = null) {
        viewModelScope.launch {
            val result = setPhotoBookmarkUseCase(
                SetPhotoBookmarkUseCase.SetBookmarkParam(
                    photoDto.id,
                    photoDto.url,
                    photoDto.width,
                    photoDto.height
                )
            )
            if (result.isSuccess) {
                photoDto.isBookmarked = true
                onSuccess?.invoke()
            } else {
                Log.e("setBookmarkFail", result.exceptionOrNull().toString())
            }
        }
    }

    fun removeBookmark(photoDto: PhotoDto, onSuccess: (() -> Unit)? = null) {
        viewModelScope.launch {
            val result = removeBookmarkUseCase(photoDto.id)
            if (result.isSuccess) {
                photoDto.isBookmarked = false
                onSuccess?.invoke()
            } else {
                Log.e("setBookmarkFail", result.exceptionOrNull().toString())
            }
        }
    }
}