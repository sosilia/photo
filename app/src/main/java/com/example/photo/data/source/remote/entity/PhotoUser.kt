package com.example.photo.data.source.remote.entity

data class PhotoUser (
    val id: String,
    val updatedAt: String,
    val username: String,
    val name: String,
    val firstName: String,
    val lastName: Any? = null,
    val twitterUsername: String,
    val portfolioURL: String,
    val bio: String,
    val location: Any? = null,
    val links: PhotoUserLinks,
    val profileImage: PhotoProfileImage,
    val instagramUsername: String,
    val totalCollections: Long,
    val totalLikes: Long,
    val totalPhotos: Long,
    val acceptedTos: Boolean,
    val forHire: Boolean,
    val social: PhotoSocial)