package com.example.photo.data.source.remote.datasource

import com.example.photo.BuildConfig
import com.example.photo.data.source.PhotoDataSource
import com.example.photo.data.source.remote.entity.Photo
import com.example.photo.data.source.remote.service.PhotoService
import javax.inject.Inject

class PhotoRemoteDataSource @Inject constructor(
    private val photoService: PhotoService
) : PhotoDataSource {
    override suspend fun getPhotos(page: Int): List<Photo> {
        return photoService.getPhotoList(BuildConfig.PHOTOS_API_KEY, page)
    }
}