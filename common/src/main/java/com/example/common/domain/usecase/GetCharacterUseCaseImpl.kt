package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetCharacterUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharacterUseCase {
    override suspend fun invoke(
        charactersUrl: List<String>,
        page: Int,
        pageSize: Int
    ): RequestState<List<Character>> {
        return withContext(Dispatchers.Default) {
            if (page > 0) delay(2000)
            val startingIndex = page * pageSize
            var rangeIndex = startingIndex + pageSize
            if (charactersUrl.size < rangeIndex) rangeIndex = charactersUrl.size
            if (startingIndex <= charactersUrl.size) {
                repository.getCharactersFromComic(
                    charactersUrl.slice(startingIndex until rangeIndex)
                )
            } else RequestState.Success(emptyList())
        }
    }
}