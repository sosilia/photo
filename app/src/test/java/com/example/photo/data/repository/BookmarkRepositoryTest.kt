package com.example.photo.data.repository

import com.example.photo.data.source.FakeBookmarkDataSource
import com.example.photo.data.source.local.entity.Bookmark
import com.example.photo.data.source.local.entity.BookmarkEntityMapper
import com.example.photo.domain.repository.BookmarkRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class BookmarkRepositoryTest {

    private val bookmark1 = Bookmark("Id1", "Url1", 0, 0)
    private val bookmark2 = Bookmark("Id2", "Url1", 0, 0)
    private val newBookmark = Bookmark("Id new", "Url new", 0, 0)
    private val localBookmarks = listOf(bookmark1, bookmark2).sortedBy { it.id }

    private lateinit var bookmarkLocalDataSource: FakeBookmarkDataSource

    // Class under test
    private lateinit var bookmarkRepository: BookmarkRepository

    private val bookmarkEntityMapper = BookmarkEntityMapper()

    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {
        bookmarkLocalDataSource = FakeBookmarkDataSource(localBookmarks.toMutableList())
        // Get a reference to the class under test
        bookmarkRepository = BookmarkRepositoryImpl(
            bookmarkLocalDataSource, bookmarkEntityMapper
        )
    }

    @Test
    fun getBookmark_getAllBookmarkFromLocalDataSource() = runBlockingTest {
        val bookmarkList = bookmarkRepository.getBookmarkList()
        assertThat(bookmarkList).isEqualTo((localBookmarks.map { bookmarkEntityMapper.mapToDomain(it) }))
    }

    @Test
    fun getBookmark_getBookmarkPageFromLocalDataSource() = runBlockingTest {
        val page = 1
        val pageSize = 1
        val bookmarkList = bookmarkRepository.getBookmarkList(page, pageSize)
        assertThat(bookmarkList).isEqualTo((localBookmarks.subList(0, 1).map { bookmarkEntityMapper.mapToDomain(it) }))
    }

    @Test
    fun saveBookmark_savesBookmarkToLocal() = runBlockingTest {
        val bookmarkDto = bookmarkEntityMapper.mapToDomain(newBookmark)
        bookmarkRepository.setBookmark(bookmarkDto)
        val bookmarkList = bookmarkRepository.getBookmarkList()
        // Verify it's in all the data sources
        assertThat(bookmarkList).contains(bookmarkDto)
    }

    @Test
    fun removeBookmark_removeBookmarkFromLocal() = runBlockingTest {
        val bookmarkId = bookmark1.id
        bookmarkRepository.removeBookmark(bookmarkId)
        val bookmarkList = bookmarkRepository.getBookmarkList()
        // Verify it's in all the data sources
        assertThat(bookmarkList).doesNotContain(bookmark1)
    }
}