package com.example.photo.util

import android.app.Activity
import android.content.Context
import android.graphics.Point
import android.view.WindowManager
import dagger.hilt.android.qualifiers.ActivityContext
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject


class ScreenUtil @Inject constructor(
    @ActivityContext private val context: Context) {

    fun getCurrentScreenWidth(): Int {
        val display = (context as Activity).windowManager.defaultDisplay
        val size = Point()
        display.getSize(size)
        val width: Int = size.x

        return width
    }
}