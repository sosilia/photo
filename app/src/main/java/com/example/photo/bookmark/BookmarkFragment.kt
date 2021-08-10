package com.example.photo.bookmark

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.example.photo.databinding.FragmentBookmarkBinding
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch

@AndroidEntryPoint
class BookmarkFragment : Fragment(){

    private val bookmarkViewModel: BookmarkViewModel by viewModels()
    private var _binding: FragmentBookmarkBinding? = null
    // This property is only valid between onCreateView and
    // onDestroyView.
    private val binding get() = _binding!!
    private val bookmarkAdapter = BookmarkRecyclerViewAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentBookmarkBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            bookmarkViewModel.flow.collectLatest { pagingData ->
                bookmarkAdapter.submitData(pagingData)
            }
        }

        initRecyclerViewAdapter()
    }

    private fun initRecyclerViewAdapter() {
        binding.recyclerView.apply {
            adapter = bookmarkAdapter
            addItemDecoration(GridSpacingItemDecoration(2, 10))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}