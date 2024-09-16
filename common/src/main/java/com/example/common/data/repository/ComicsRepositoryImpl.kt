package com.example.common.data.repository

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ComicsRepositoryImpl(
    private val apiService: MarvelComicsApiService,
    private val mongoDbService: MongoDbService
) : ComicsRepository {
    override suspend fun getComicsFromApi(): RequestState<List<Comic>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                RequestState.Success(apiService.getComics())
            }.getOrElse {
                RequestState.Error("")
            }
        }
    }

    override suspend fun fetchLocalComicsRealTime(): Flow<List<Comic>> {
        return withContext(Dispatchers.IO) {
            mongoDbService.fetchComicsRealTime()
        }
    }

    override suspend fun saveComic(comic: Comic, characters: List<Character>) {
        return withContext(Dispatchers.IO) {
            runCatching {
                mongoDbService.saveComic(comic, characters)
            }
        }
    }

    override suspend fun deleteComic(comic: Comic) {
        withContext(Dispatchers.IO) {
            runCatching {
                mongoDbService.deleteComic(comic)
            }
        }
    }
}