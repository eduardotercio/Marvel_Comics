package com.example.common.data.repository

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService

class CharacterRepositoryImpl(
    private val apiService: MarvelComicsApiService,
    private val mongoDbService: MongoDbService
) : CharacterRepository {
    override suspend fun getCharactersPagination(
        charactersUrl: List<String>,
        comicId: Int,
        range: IntRange
    ): RequestState<List<Character>> {
        return runCatching {
            val localResponse = mongoDbService.getCharactersFromComic(comicId)
            if (localResponse is RequestState.Success && localResponse.data.isNotEmpty()) {
                return RequestState.Success(localResponse.data.slice(range))
            } else {
                val response = apiService.getCharactersFromComic(charactersUrl.slice(range))
                RequestState.Success(response)
            }
        }.getOrElse {
            RequestState.Error("Error getting characters, try again later.")
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