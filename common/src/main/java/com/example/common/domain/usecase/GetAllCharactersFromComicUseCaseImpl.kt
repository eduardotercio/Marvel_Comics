package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository

class GetAllCharactersFromComicUseCaseImpl(
    private val repository: CharacterRepository
) : GetAllCharactersFromComicUseCase {
    override suspend fun invoke(charactersUrl: List<String>): RequestState<List<Character>> {
        return repository.getAllCharactersFromComic(charactersUrl)
    }
}