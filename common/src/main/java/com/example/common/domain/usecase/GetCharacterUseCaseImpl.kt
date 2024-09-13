package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository

class GetCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterUseCase {
    override suspend fun invoke(charactersUrl: List<String>): RequestState<List<Character>> {
        return repository.getCharactersFromComic(charactersUrl)
    }
}