package com.example.common.data.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.service.remote.MarvelComicsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class ComicsRepositoryImpl(
    private val apiService: MarvelComicsApiService
) : ComicsRepository {
    override suspend fun getComics(getFromLocal: Boolean): RequestState<List<Comic>> {
        return withContext(Dispatchers.IO) {
            if (getFromLocal) {
                apiService.getComics() //TODO
            } else {
                apiService.getComics()
            }
        }
    }
}