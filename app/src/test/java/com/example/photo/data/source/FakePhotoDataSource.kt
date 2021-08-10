package com.example.photo.data.source

import com.example.photo.data.source.PhotoDataSource
import com.example.photo.data.source.remote.entity.Photo

class FakePhotoDataSource(var photoList: MutableList<Photo>? = mutableListOf()) :
    PhotoDataSource {

    override suspend fun getPhotos(page: Int): List<Photo> {
        val size = photoList?.size ?: 0
        val from = (page - 1) * 10
        val to = if (size > page * 10) page * 10 else size
        return photoList?.subList(from, to) ?: emptyList()
    }
}