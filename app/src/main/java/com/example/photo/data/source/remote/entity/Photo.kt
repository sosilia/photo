package com.example.photo.data.source.remote.entity

import com.example.photo.data.Entity
import com.example.photo.data.EntityMapper
import com.example.photo.domain.model.PhotoDto
import javax.inject.Inject

data class Photo (
    val id: String,
    val createdAt: String?,
    val updatedAt: String?,
    val promotedAt: String?,
    val width: Int,
    val height: Int,
    val color: String?,
    val blurHash: String?,
    val description: String?,
    val altDescription: String?,
    val urls: PhotoUrls,
    val links: PhotoLinks?,
    val likes: Long?,
    val likedByUser: Boolean?,
    val sponsorship: PhotoSponsorship?,
    val user: PhotoUser?) : Entity()

class PhotoEntityMapper @Inject constructor() : EntityMapper<PhotoDto, Photo> {

    override fun mapToDomain(entity: Photo): PhotoDto = PhotoDto(
        entity.id,
        entity.urls.small,
        entity.width,
        entity.height)

    override fun mapToEntity(model: PhotoDto): Photo {
        TODO("Not yet implemented")
    }
}

