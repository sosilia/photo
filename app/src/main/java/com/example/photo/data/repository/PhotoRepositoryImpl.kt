package com.example.photo.data.repository

import com.example.photo.data.source.BookmarkDataSource
import com.example.photo.data.source.PhotoDataSource
import com.example.photo.data.source.local.datasource.BookmarkLocalDataSource
import com.example.photo.data.source.remote.datasource.PhotoRemoteDataSource
import com.example.photo.data.source.remote.entity.PhotoEntityMapper
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.repository.PhotoRepository
import javax.inject.Inject

class PhotoRepositoryImpl @Inject constructor(
    private val localDataSource: BookmarkDataSource,
    private val remoteDataSource: PhotoDataSource,
    private val photoEntityMapper: PhotoEntityMapper,
) : PhotoRepository {
    override suspend fun getPhotos(page: Int): List<PhotoDto> {
        val bookmarkedPhotoIdSet = localDataSource.getBookmarkList()
            .map {
                it.id
            }.toSet()
        return remoteDataSource.getPhotos(page).map {
            photoEntityMapper.mapToDomain(it).apply {
                isBookmarked = bookmarkedPhotoIdSet.contains(it.id)
            }
        }
    }
}