package com.example.photo.domain.usecase

import com.example.photo.CoroutineUseCase
import com.example.photo.di.IoDispatcher
import com.example.photo.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class RemoveBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<String, Unit>(coroutineDispatcher) {

    override suspend fun execute(parameter: String) {
        bookmarkRepository.removeBookmark(parameter)
    }
}