package com.example.common.domain.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character

interface CharacterRepository {
    suspend fun getCharactersFromComic(charactersUrl: List<String>): RequestState<List<Character>>
}