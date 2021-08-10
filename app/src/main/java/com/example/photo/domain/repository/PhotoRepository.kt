package com.example.photo.domain.repository

import com.example.photo.domain.model.PhotoDto

interface PhotoRepository {
    suspend fun getPhotos(page : Int) : List<PhotoDto>
}