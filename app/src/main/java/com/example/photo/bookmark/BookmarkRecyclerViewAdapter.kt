package com.example.photo.bookmark

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photo.R
import com.example.photo.databinding.LayoutBookmarkItemBinding
import com.example.photo.databinding.LayoutPhotoItemBinding
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto

class BookmarkRecyclerViewAdapter : PagingDataAdapter<BookmarkDto, ViewHolder>(BookmarkDiffCallback()) {

    interface OnBookmarkClickListener {
        fun onBookmarkClick(position : Int, bookmark: BookmarkDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }
}

class ViewHolder private constructor(private val binding: LayoutBookmarkItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(item: BookmarkDto?) {
        item?.let {
            Glide
                .with(binding.imageView)
                .asBitmap()
                .load(it.url)
                .centerCrop()
                .into(binding.imageView)
        }
    }

    fun onViewDetachedFromWindow() {
        binding.imageView.clearAnimation()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutBookmarkItemBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(binding)
        }
    }
}

class BookmarkDiffCallback : DiffUtil.ItemCallback<BookmarkDto>() {
    override fun areItemsTheSame(oldItem: BookmarkDto, newItem: BookmarkDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: BookmarkDto, newItem: BookmarkDto): Boolean {
        return oldItem == newItem
    }
}