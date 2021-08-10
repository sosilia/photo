package com.example.photo.data.source.local

import androidx.room.TypeConverter
import java.util.*

object Converter {
    @TypeConverter
    @JvmStatic
    fun toDate(dateLong: Long?): Date? {
        return dateLong?.let { Date(it) }
    }

    @TypeConverter
    @JvmStatic
    fun fromDate(date: Date?): Long? {
        return date?.time
    }
}