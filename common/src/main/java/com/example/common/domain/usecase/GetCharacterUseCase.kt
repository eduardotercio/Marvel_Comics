package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character

interface GetCharacterUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
        charactersUrl: List<String>,
        comicId: Int
    ): RequestState<List<Character>>
}