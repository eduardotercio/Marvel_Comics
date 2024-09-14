package com.example.common.domain.service.local

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface MongoDbService {

    suspend fun saveComic(comic: Comic, characters: List<Character>)

    suspend fun getComics(): RequestState<List<Comic>>

    suspend fun fetchComicsRealTime(): Flow<List<Comic>>

    suspend fun getCharactersFromComic(comicId: Int): RequestState<List<Character>>

    suspend fun deleteComic(comic: Comic)

    suspend fun cleanDatabase()
}