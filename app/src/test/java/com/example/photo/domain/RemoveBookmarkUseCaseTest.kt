package com.example.photo.domain

import com.example.photo.data.source.FakeBookmarkRepository
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.usecase.RemoveBookmarkUseCase
import com.example.photo.domain.usecase.SetPhotoBookmarkUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class RemoveBookmarkUseCaseTest {

    private val repository = FakeBookmarkRepository()

    private val useCase = RemoveBookmarkUseCase(repository, TestCoroutineDispatcher())

    @Test
    fun removePhotoBookmark_success() = runBlockingTest {
        val bookmarkDto = BookmarkDto("id1", "url1", 0, 0)
        repository.addBookmark(bookmarkDto)
        val result = useCase("id1")
        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(repository.getBookmarkList()).isEmpty()
    }

    @Test
    fun setPhotoBookmark_success_not_remove() = runBlockingTest {
        val bookmarkDto = BookmarkDto("id1", "url1", 0, 0)
        repository.addBookmark(bookmarkDto)

        val result = useCase("url1")

        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(repository.getBookmarkList()).hasSize(1)
    }

    @Test
    fun setPhotoBookmark_fail() = runBlockingTest {
        repository.setReturnError(true)

        val result = useCase("url1")

        // Verify the result is a success and empty
        assertThat(result.isFailure).isEqualTo(true)
    }
}