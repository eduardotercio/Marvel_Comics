package com.example.common.data.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext

class ComicsRepositoryImpl(
    private val apiService: MarvelComicsApiService,
    private val mongoDbService: MongoDbService
) : ComicsRepository {
    override suspend fun getComics(onlyLocal: Boolean): RequestState<List<Comic>> {
        return withContext(Dispatchers.Default) {
            if (onlyLocal) {
                mongoDbService.getComics()
            } else {
                val localDeferred = async { mongoDbService.getComics() }
                val apiDeferred = async { apiService.getComics() }

                val localResponse = localDeferred.await()
                val apiResponse = apiDeferred.await()

                if (localResponse is RequestState.Success && apiResponse is RequestState.Success) {
                    val localComics = localResponse.data
                    val apiComics = apiResponse.data.map { comic ->
                        val localComic = localComics.find { it.id == comic.id }

                        if (localComic != null) {
                            comic.copy(isFavorite = true)
                        } else {
                            comic
                        }
                    }.distinctBy { it.id }
                    RequestState.Success(apiComics)
                } else {
                    RequestState.Error("Error on getting comics, try again later.")
                }
            }
        }
    }

    override suspend fun fetchComicsRealTime(): Flow<List<Comic>> {
        return mongoDbService.fetchComicsRealTime()
    }

    override suspend fun saveComic(comic: Comic, characters: List<Character>) {
        return withContext(Dispatchers.IO) {
            mongoDbService.saveComic(comic, characters)
        }
    }

    override suspend fun deleteComic(comic: Comic) {
        withContext(Dispatchers.IO) {
            mongoDbService.deleteComic(comic)
        }
    }
}