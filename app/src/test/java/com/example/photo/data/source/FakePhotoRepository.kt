package com.example.photo.data.source

import com.example.photo.data.source.remote.entity.Photo
import com.example.photo.data.source.remote.entity.PhotoEntityMapper
import com.example.photo.data.source.remote.entity.PhotoUrls
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.repository.PhotoRepository

class FakePhotoRepository : PhotoRepository {

    private var shouldReturnError = false

    fun setReturnError(value: Boolean) {
        shouldReturnError = value
    }

    fun addPhoto(value : PhotoDto) {
        photoList.add(value)
    }

    private val photoList = mutableListOf<PhotoDto>()

    override suspend fun getPhotos(page: Int): List<PhotoDto> {

        if (shouldReturnError) {
            throw Exception("Test exception")
        }

        val size = photoList.size
        val from = (page - 1) * 10
        val to = if (size > page * 10) page * 10 else size
        return photoList.subList(from, to)
    }
}