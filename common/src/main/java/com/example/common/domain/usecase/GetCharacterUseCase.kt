package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character

interface GetCharacterUseCase {
    suspend operator fun invoke(
        charactersUrl: List<String>,
        page: Int,
        pageSize: Int
    ): RequestState<List<Character>>
}