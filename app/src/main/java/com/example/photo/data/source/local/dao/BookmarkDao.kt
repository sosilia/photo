package com.example.photo.data.source.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.photo.data.source.local.entity.Bookmark

@Dao
interface BookmarkDao {
    @Query("SELECT * FROM Bookmark ORDER BY creationDate DESC")
    fun getAllBookmarks(): List<Bookmark>

    @Query("SELECT * FROM Bookmark ORDER BY creationDate DESC LIMIT :limit OFFSET :offset")
    fun getBookmarks(limit : Int, offset : Int): List<Bookmark>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertBookmark(bookmark: Bookmark)

    @Query("DELETE FROM bookmark WHERE id = :id")
    fun deleteBookmark(id : String)
}