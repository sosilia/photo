package com.example.photo.bookmark

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.cachedIn
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class BookmarkViewModel @Inject constructor(
    private val bookmarkPagingSource: BookmarkPagingSource,
) : ViewModel() {
    val flow = Pager(PagingConfig(pageSize = 10)) {
        bookmarkPagingSource
    }.flow.cachedIn(viewModelScope)
}