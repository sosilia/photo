package com.example.photo.domain

import com.example.photo.data.source.FakeBookmarkRepository
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.usecase.GetBookmarkUseCase
import com.example.photo.domain.usecase.GetPhotoUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetBookmarkUseCaseTest {

    private val bookmarkRepository = FakeBookmarkRepository()

    private val useCase = GetBookmarkUseCase(bookmarkRepository, TestCoroutineDispatcher())

    @Test
    fun getBookmark_empty() = runBlockingTest {
        val page = 1
        val pageSize = 10
        val result = useCase(GetBookmarkUseCase.GetBookmarkParam(page, pageSize))

        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()).isEmpty()
    }

    @Test
    fun getBookmark_error() = runBlockingTest {
        bookmarkRepository.setReturnError(true)

        val page = 1
        val pageSize = 10
        val result = useCase(GetBookmarkUseCase.GetBookmarkParam(page, pageSize))

        // Verify the result is a success and empty
        assertThat(result.isFailure).isEqualTo(true)
    }

    @Test
    fun getPhotos_all() = runBlockingTest {
        bookmarkRepository.addBookmark(BookmarkDto("id1", "url1", 0, 0))
        bookmarkRepository.addBookmark(BookmarkDto("id2", "url2", 0, 0))
        bookmarkRepository.addBookmark(BookmarkDto("id3", "url3", 0, 0))

        val page = 1
        val pageSize = 10
        val result = useCase(GetBookmarkUseCase.GetBookmarkParam(page, pageSize))

        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()?.size).isEqualTo(3)
    }
}