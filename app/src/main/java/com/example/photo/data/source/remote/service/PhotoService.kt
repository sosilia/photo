package com.example.photo.data.source.remote.service

import com.example.photo.data.source.remote.entity.Photo
import retrofit2.http.GET
import retrofit2.http.Query

interface PhotoService {

    @GET("/photos")
    suspend fun getPhotoList(
        @Query("client_id") clientId: String,
        @Query("page") page: Int
    ): List<Photo>
}