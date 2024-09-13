package com.example.common.domain.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

interface ComicsRepository {
    suspend fun getComics(getFromLocal: Boolean = false): RequestState<List<Comic>>

    suspend fun saveComic(comic: Comic, characters: List<Character>)
}