package com.example.photo.domain.usecase

import com.example.photo.CoroutineUseCase
import com.example.photo.di.IoDispatcher
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.repository.PhotoRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetPhotoUseCase @Inject constructor(
    private val photoRepository: PhotoRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher,
) : CoroutineUseCase<GetPhotoUseCase.GetPhotoParam, List<PhotoDto>>(coroutineDispatcher) {

    data class GetPhotoParam(val page: Int)

    override suspend fun execute(parameter: GetPhotoParam): List<PhotoDto> {
        return photoRepository.getPhotos(parameter.page)
    }
}