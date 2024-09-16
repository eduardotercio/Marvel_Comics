package com.example.common.data.repository

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val apiService: MarvelComicsApiService,
    private val mongoDbService: MongoDbService
) : CharacterRepository {
    override suspend fun getCharactersPagination(
        charactersUrl: List<String>,
        comicId: Int,
        range: IntRange
    ): RequestState<List<Character>> {
        return withContext(Dispatchers.IO) {
            runCatching {
                val localCharacters = mongoDbService.getCharactersFromComic(comicId)
                if (localCharacters.isNotEmpty()) {
                    RequestState.Success(localCharacters.slice(range))
                } else {
                    val remoteCharacters =
                        apiService.getCharactersFromComic(charactersUrl.slice(range))
                    RequestState.Success(remoteCharacters)
                }
            }.getOrElse {
                RequestState.Error("${it.message}")
            }
        }
    }

    override suspend fun getAllCharactersFromComic(
        charactersUrl: List<String>
    ): RequestState<List<Character>> {
        return runCatching {
            val response = apiService.getCharactersFromComic(charactersUrl)

            RequestState.Success(response)
        }.getOrElse {
            RequestState.Error("Error getting characters, try again later.")
        }
    }
}