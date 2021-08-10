package com.example.photo.data.repository

import com.example.photo.data.source.FakeBookmarkDataSource
import com.example.photo.data.source.FakePhotoDataSource
import com.example.photo.data.source.local.entity.Bookmark
import com.example.photo.data.source.remote.entity.Photo
import com.example.photo.data.source.remote.entity.PhotoEntityMapper
import com.example.photo.data.source.remote.entity.PhotoUrls
import com.example.photo.domain.repository.PhotoRepository
import com.google.common.truth.Truth.assertThat
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Test

@ExperimentalCoroutinesApi
class PhotoRepositoryTest {

    private val photo1 = Photo(
        "Id1",
        null,
        null,
        null,
        1,
        1,
        null,
        null,
        null,
        null,
        PhotoUrls("raw1", "full1", "regular1", "small1", "thumb1"),
        null,
        null,
        null,
        null,
        null
    )
    private val photo2 = Photo(
        "Id2",
        null,
        null,
        null,
        1,
        1,
        null,
        null,
        null,
        null,
        PhotoUrls("raw2", "full2", "regular2", "small2", "thumb2"),
        null,
        null,
        null,
        null,
        null
    )
    private val bookmark1 = Bookmark("Id1", "small1", 0, 0)
    private val remotePhotos = listOf(photo1, photo2).sortedBy { it.id }
    private val localBookmarks = listOf(bookmark1).sortedBy { it.id }
    private val filteredRemotePhotos = listOf(photo2).sortedBy { it.id }

    private lateinit var photoRemoteDataSource: FakePhotoDataSource
    private lateinit var bookmarkLocalDataSource: FakeBookmarkDataSource
    private lateinit var photoRepository: PhotoRepository

    private val photoEntityMapper = PhotoEntityMapper()

    @ExperimentalCoroutinesApi
    @Before
    fun createRepository() {
        photoRemoteDataSource = FakePhotoDataSource(remotePhotos.toMutableList())
        bookmarkLocalDataSource = FakeBookmarkDataSource()
        // Get a reference to the class under test
        photoRepository = PhotoRepositoryImpl(
            bookmarkLocalDataSource, photoRemoteDataSource, photoEntityMapper
        )
    }

    @Test
    fun getPhoto_getPagePhoto() = runBlockingTest {
        val page = 1
        val photoList = photoRepository.getPhotos(page)
        assertThat(photoList).isEqualTo((remotePhotos.map { photoEntityMapper.mapToDomain(it) }))
    }

    @Test
    fun getPhoto_getPagePhotoWithBookmark() = runBlockingTest {
        bookmarkLocalDataSource.bookmarkList = localBookmarks.toMutableList()

        val page = 1
        val photoList = photoRepository.getPhotos(page)
        assertThat(photoList.first { it.id == bookmark1.id }.isBookmarked).isEqualTo(true)
    }
}