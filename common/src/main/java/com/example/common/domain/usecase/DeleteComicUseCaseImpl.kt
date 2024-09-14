package com.example.common.domain.usecase

import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository

class DeleteComicUseCaseImpl(
    private val repository: ComicsRepository
) : DeleteComicUseCase {
    override suspend fun invoke(comic: Comic) {
        repository.deleteComic(comic)
    }
}