package com.example.photo.data.source.remote.entity

data class PhotoSponsorship (
    val impressionUrls: List<String>,
    val tagline: String,
    val taglineURL: String,
    val sponsor: PhotoUser)