package com.example.common.domain.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface ComicsRepository {
    suspend fun getComicsFromApi(): RequestState<List<Comic>>

    suspend fun fetchLocalComicsRealTime(): Flow<List<Comic>>

    suspend fun saveComic(comic: Comic, characters: List<Character>)

    suspend fun deleteComic(comic: Comic)
}