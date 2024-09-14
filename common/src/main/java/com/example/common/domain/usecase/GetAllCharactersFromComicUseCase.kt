package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character

interface GetAllCharactersFromComicUseCase {

    suspend operator fun invoke(charactersUrl: List<String>): RequestState<List<Character>>
}