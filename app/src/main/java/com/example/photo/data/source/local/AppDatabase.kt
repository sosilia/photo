package com.example.photo.data.source.local

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.photo.data.source.local.dao.BookmarkDao
import com.example.photo.data.source.local.entity.Bookmark

@Database(entities = [Bookmark::class], version = 1)
@TypeConverters(Converter::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun bookmarkDao(): BookmarkDao
}