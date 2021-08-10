package com.example.photo.domain.usecase

import com.example.photo.CoroutineUseCase
import com.example.photo.di.IoDispatcher
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class SetPhotoBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<SetPhotoBookmarkUseCase.SetBookmarkParam, Unit>(coroutineDispatcher) {

    data class SetBookmarkParam(
        val id: String,
        val url: String,
        val width: Int,
        val height: Int,
    )

    override suspend fun execute(parameter: SetBookmarkParam) {
        bookmarkRepository.setBookmark(
            BookmarkDto(
                parameter.id,
                parameter.url,
                parameter.width,
                parameter.height
            )
        )
    }
}