package com.example.common.domain.usecase

import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

interface SaveComicUseCase {

    suspend operator fun invoke(comic: Comic, characters: List<Character>)
}