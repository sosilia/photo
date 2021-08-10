package com.example.photo.data.source.local.entity

import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.photo.data.EntityMapper
import com.example.photo.data.source.local.Converter
import com.example.photo.domain.model.BookmarkDto
import java.util.Date
import javax.inject.Inject

@Entity
class Bookmark(
    @PrimaryKey val id: String,
    val photoUrl: String,
    val width: Int,
    val height: Int,
    val creationDate: Date = Date(System.currentTimeMillis())
) : com.example.photo.data.Entity()

class BookmarkEntityMapper @Inject constructor() : EntityMapper<BookmarkDto, Bookmark> {
    override fun mapToDomain(entity: Bookmark): BookmarkDto =
        BookmarkDto(entity.id, entity.photoUrl, entity.width, entity.height)

    override fun mapToEntity(model: BookmarkDto): Bookmark =
        Bookmark(model.id, model.url, model.width, model.height)
}