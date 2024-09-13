package com.example.common.domain.service.local

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

interface MongoDbService {

    suspend fun saveComic(comic: Comic, characters: List<Character>)

    suspend fun getComics(): RequestState<List<Comic>>

    suspend fun getCharactersFromComic(comicId: Int): RequestState<List<Character>>
}