package com.example.common.domain.service.remote

import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

interface MarvelComicsApiService {
    suspend fun getComics(): List<Comic>

    suspend fun getCharactersFromComic(charactersUrl: List<String>): List<Character>
}