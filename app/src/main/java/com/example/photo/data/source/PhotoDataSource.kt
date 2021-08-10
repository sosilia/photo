package com.example.photo.data.source

import com.example.photo.data.source.remote.entity.Photo

interface PhotoDataSource {
    suspend fun getPhotos(page : Int) : List<Photo>
}