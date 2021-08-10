package com.example.photo.domain

import com.example.photo.data.source.FakePhotoRepository
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.usecase.GetPhotoUseCase
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Test

@ExperimentalCoroutinesApi
class GetPhotoUseCaseTest {

    private val photoRepository = FakePhotoRepository()

    private val useCase = GetPhotoUseCase(photoRepository, TestCoroutineDispatcher())

    @Test
    fun getPhotos_empty() = runBlockingTest {
        val page = 1
        val result = useCase(GetPhotoUseCase.GetPhotoParam(page))

        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()).isEmpty()
    }

    @Test
    fun getPhotos_error() = runBlockingTest {
        photoRepository.setReturnError(true)

        val page = 1
        val result = useCase(GetPhotoUseCase.GetPhotoParam(page))

        // Verify the result is a success and empty
        assertThat(result.isFailure).isEqualTo(true)
    }

    @Test
    fun getPhotos_all() = runBlockingTest {
        photoRepository.addPhoto(PhotoDto("id1", "url1", 0, 0))
        photoRepository.addPhoto(PhotoDto("id2", "url2", 0, 0))

        val page = 1
        val result = useCase(GetPhotoUseCase.GetPhotoParam(page))

        // Verify the result is a success and empty
        assertThat(result.isSuccess).isEqualTo(true)
        assertThat(result.getOrNull()).hasSize(2)
    }
}