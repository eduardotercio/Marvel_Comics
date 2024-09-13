package com.example.common.domain.service.remote

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

interface MarvelComicsApiService {
    suspend fun getComics(): RequestState<List<Comic>>

    suspend fun getCharactersFromComic(charactersUrl: List<String>): RequestState<List<Character>>
}