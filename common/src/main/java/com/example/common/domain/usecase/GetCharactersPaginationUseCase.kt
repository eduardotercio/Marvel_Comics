package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character

interface GetCharactersPaginationUseCase {
    suspend operator fun invoke(
        page: Int,
        pageSize: Int,
        charactersUrl: List<String>,
        comicId: Int
    ): RequestState<List<Character>>
}