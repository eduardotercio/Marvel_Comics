package com.example.common.domain.service.remote

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Comic

interface MarvelComicsApiService {
    suspend fun getComics(): RequestState<List<Comic>>
}