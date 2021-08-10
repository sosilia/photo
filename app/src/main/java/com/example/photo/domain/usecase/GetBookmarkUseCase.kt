package com.example.photo.domain.usecase

import com.example.photo.CoroutineUseCase
import com.example.photo.di.IoDispatcher
import com.example.photo.domain.model.BookmarkDto
import com.example.photo.domain.model.PhotoDto
import com.example.photo.domain.repository.BookmarkRepository
import kotlinx.coroutines.CoroutineDispatcher
import javax.inject.Inject

class GetBookmarkUseCase @Inject constructor(
    private val bookmarkRepository: BookmarkRepository,
    @IoDispatcher coroutineDispatcher: CoroutineDispatcher
) : CoroutineUseCase<GetBookmarkUseCase.GetBookmarkParam, List<BookmarkDto>>(coroutineDispatcher) {

    data class GetBookmarkParam(val page : Int, val pageSize : Int)

    override suspend fun execute(parameter: GetBookmarkParam): List<BookmarkDto> {
        return bookmarkRepository.getBookmarkList(parameter.page, parameter.pageSize)
    }
}