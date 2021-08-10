package com.example.photo.photo

import android.graphics.Rect
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ItemDecoration
import androidx.recyclerview.widget.StaggeredGridLayoutManager

class StaggeredGridSpacingItemDecoration(private val spacing: Int) : ItemDecoration() {

    override fun getItemOffsets(outRect: Rect, view: View, parent: RecyclerView, state: RecyclerView.State) {
        val position = parent.getChildAdapterPosition(view)
        val lp = view.layoutParams as StaggeredGridLayoutManager.LayoutParams
        val spanIndex = lp.spanIndex

        if (position >= 0) {
            if (spanIndex == 1) {
                outRect.left = spacing
            } else {
                outRect.right = spacing
            }
            outRect.bottom = spacing * 2
        }
    }
}