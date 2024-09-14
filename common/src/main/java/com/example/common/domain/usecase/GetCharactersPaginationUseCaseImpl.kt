package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.withContext

class GetCharactersPaginationUseCaseImpl(
    private val repository: CharacterRepository
) : GetCharactersPaginationUseCase {
    override suspend fun invoke(
        page: Int,
        pageSize: Int,
        charactersUrl: List<String>,
        comicId: Int
    ): RequestState<List<Character>> {
        return withContext(Dispatchers.Default) {
            if (page > 0) delay(2000)

            val startingIndex = page * pageSize
            var lastIndex = startingIndex + pageSize - 1
            if (charactersUrl.size < lastIndex) lastIndex = charactersUrl.size - 1

            val range = IntRange(startingIndex, lastIndex)
            if (startingIndex <= charactersUrl.size) {
                repository.getCharactersPagination(
                    charactersUrl,
                    comicId,
                    range
                )
            } else RequestState.Success(emptyList())
        }
    }
}