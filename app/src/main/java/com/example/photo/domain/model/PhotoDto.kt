package com.example.photo.domain.model

data class PhotoDto(
    val id : String,
    val url : String,
    val width : Int,
    val height : Int,
    var isBookmarked : Boolean = false,
) : Model()