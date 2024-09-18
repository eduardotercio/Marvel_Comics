package com.example.common.domain.service.local

import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface MongoDbService {

    suspend fun saveComic(comic: Comic, characters: List<Character>)

    suspend fun fetchComicsRealTime(): Flow<List<Comic>>

    suspend fun getCharactersFromComic(comicId: Int): List<Character>

    suspend fun deleteComic(comic: Comic)

    suspend fun cleanDatabase()
}