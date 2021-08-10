package com.example.photo.photo

import android.content.res.ColorStateList
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.photo.R
import com.example.photo.databinding.LayoutPhotoItemBinding
import com.example.photo.domain.model.PhotoDto
import com.example.photo.util.ScreenUtil
import javax.inject.Inject

class PhotoRecyclerViewAdapter(
    private val onPhotoBookmarkClickListener: OnPhotoBookmarkClickListener? = null
) : PagingDataAdapter<PhotoDto, ViewHolder>(PhotoDiffCallback()) {

    interface OnPhotoBookmarkClickListener {
        fun onPhotoBookmarkClick(position : Int, photoDto: PhotoDto)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder.from(parent)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item, onPhotoBookmarkClickListener)
    }

    override fun onViewDetachedFromWindow(holder: ViewHolder) {
        super.onViewDetachedFromWindow(holder)
        holder.onViewDetachedFromWindow()
    }
}

class ViewHolder private constructor(private val binding: LayoutPhotoItemBinding) :
    RecyclerView.ViewHolder(binding.root) {

    fun bind(
        item: PhotoDto?,
        onPhotoBookmarkClickListener: PhotoRecyclerViewAdapter.OnPhotoBookmarkClickListener?
    ) {

        item?.let {
            Glide
                .with(binding.imageView)
                .asBitmap()
                .override(it.width, it.height)
                .load(it.url)
                .centerCrop()
                .fitCenter()
                .into(binding.imageView)

            binding.imageView.layoutParams.height = it.height

            binding.imageViewBookmark.setOnClickListener {
                onPhotoBookmarkClickListener?.onPhotoBookmarkClick(bindingAdapterPosition, item)
            }

            binding.imageViewBookmark.imageTintList =
                if (item.isBookmarked) {
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.red
                        )
                    )
                } else {
                    ColorStateList.valueOf(
                        ContextCompat.getColor(
                            binding.root.context,
                            R.color.black
                        )
                    )
                }
        }
    }

    fun onViewDetachedFromWindow() {
        binding.imageView.clearAnimation()
    }

    companion object {
        fun from(parent: ViewGroup): ViewHolder {
            val layoutInflater = LayoutInflater.from(parent.context)
            val binding = LayoutPhotoItemBinding.inflate(layoutInflater, parent, false)

            return ViewHolder(binding)
        }
    }
}

class PhotoDiffCallback : DiffUtil.ItemCallback<PhotoDto>() {
    override fun areItemsTheSame(oldItem: PhotoDto, newItem: PhotoDto): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: PhotoDto, newItem: PhotoDto): Boolean {
        return oldItem == newItem
    }
}