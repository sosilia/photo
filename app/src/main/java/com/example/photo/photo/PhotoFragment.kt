package com.example.photo.photo

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.paging.map
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.example.photo.databinding.FragmentPhotoBinding
import com.example.photo.domain.model.PhotoDto
import com.example.photo.util.ScreenUtil
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class PhotoFragment : Fragment(), PhotoRecyclerViewAdapter.OnPhotoBookmarkClickListener {

    @Inject
    lateinit var screenUtil: ScreenUtil

    private val photoViewModel: PhotoViewModel by viewModels()
    private var _binding: FragmentPhotoBinding? = null
    private val binding get() = _binding!!
    private val photoAdapter = PhotoRecyclerViewAdapter(this)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPhotoBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            photoViewModel.flow.collectLatest { pagingData ->
                photoAdapter.submitData(pagingData.map {
                    val screenSize = screenUtil.getCurrentScreenWidth() / 2
                    val ratio =
                        if (it.width > screenSize) {
                            screenSize / it.width.toFloat()
                        } else {
                            it.width.toFloat() / screenSize
                        }
                    val fixedWidth = it.width * ratio
                    val fixedHeight = it.height * ratio

                    PhotoDto(it.id, it.url, fixedWidth.toInt(), fixedHeight.toInt(), it.isBookmarked)
                })
            }
        }

        initRecyclerViewAdapter()
    }

    private fun initRecyclerViewAdapter() {
        binding.recyclerView.apply {
            adapter = photoAdapter
            (layoutManager as StaggeredGridLayoutManager).gapStrategy =
                StaggeredGridLayoutManager.GAP_HANDLING_MOVE_ITEMS_BETWEEN_SPANS
            itemAnimator = null
            addItemDecoration(StaggeredGridSpacingItemDecoration(10))
        }
    }

    override fun onPhotoBookmarkClick(position : Int, photoDto: PhotoDto) {
        if (photoDto.isBookmarked) {
            photoViewModel.removeBookmark(photoDto) {
                photoAdapter.notifyItemChanged(position)
            }
        } else {
            photoViewModel.setBookMark(photoDto) {
                photoAdapter.notifyItemChanged(position)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}