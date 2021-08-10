package com.example.photo.domain.model

data class BookmarkDto(
    val id : String,
    val url : String,
    val width : Int,
    val height : Int,
) : Model()