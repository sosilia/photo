package com.example.photo.domain

import com.example.photo.data.source.FakeBookmarkRepository
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.usecase.SetPhotoBookmarkUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class SetPhotoBookmarkUseCaseTest {

    private val repository = FakeBookmarkRepository()

    private val useCase = SetPhotoBookmarkUseCase(repository, TestCoroutineDispatcher())

    @Test
    fun setPhotoBookmark_success() = runBlockingTest {
        val bookmarkDto = BookmarkDto("id1", "url1", 0, 0)
        val result = useCase(SetPhotoBookmarkUseCase.SetBookmarkParam(bookmarkDto.id, bookmarkDto.url, bookmarkDto.width, bookmarkDto.height))

        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(repository.getBookmarkList().get(0)).isEqualTo(bookmarkDto)
    }

    @Test
    fun setPhotoBookmark_fail() = runBlockingTest {
        repository.setReturnError(true)

        val result = useCase(SetPhotoBookmarkUseCase.SetBookmarkParam("id1", "url1", 0, 0))

        assertThat(result.isFailure).isEqualTo(true)
    }
}