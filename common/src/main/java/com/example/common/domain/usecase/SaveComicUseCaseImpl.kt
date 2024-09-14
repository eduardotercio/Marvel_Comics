package com.example.common.domain.usecase

import com.example.common.data.repository.ComicsRepositoryImpl
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

class SaveComicUseCaseImpl(
    private val repository: ComicsRepositoryImpl
) : SaveComicUseCase {
    override suspend fun invoke(comic: Comic, characters: List<Character>) {
        repository.saveComic(comic, characters)
    }
}