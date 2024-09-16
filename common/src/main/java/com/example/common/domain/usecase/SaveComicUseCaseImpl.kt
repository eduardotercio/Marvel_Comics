package com.example.common.domain.usecase

import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository

class SaveComicUseCaseImpl(
    private val repository: ComicsRepository
) : SaveComicUseCase {
    override suspend fun invoke(comic: Comic, characters: List<Character>) {
        repository.saveComic(comic, characters)
    }
}