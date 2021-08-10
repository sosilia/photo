package com.example.photo

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.commit
import com.example.photo.bookmark.BookmarkFragment
import com.example.photo.databinding.ActivityMainBinding
import com.example.photo.photo.PhotoFragment
import com.example.photo.util.ScreenUtil
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private val photoFragment by lazy {
        PhotoFragment()
    }
    private val bookmarkFragment by lazy {
        BookmarkFragment()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root

        setContentView(view)

        setSupportActionBar(binding.toolBar)

        replaceInitialFragment()
        initBottomNavigation()
    }

    private fun replaceInitialFragment() {
        supportFragmentManager.commit {
            replace(R.id.fragment_container, PhotoFragment())
        }
    }

    private fun initBottomNavigation() {
        binding.bottomNavigation.setOnItemSelectedListener {
            if (binding.bottomNavigation.selectedItemId == it.itemId) {
                return@setOnItemSelectedListener true
            }

            when (it.itemId) {
                R.id.action_go_to_photo_list -> {
                    supportFragmentManager.commit {
                        replace(R.id.fragment_container, PhotoFragment())
                    }
                }

                R.id.action_go_to_bookmark_list -> supportFragmentManager.commit {
                    replace(R.id.fragment_container, BookmarkFragment())
                }
            }
            true
        }
    }
}