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
                var characters = mongoDbService.getCharactersFromComic(comicId)
                if (characters.isNotEmpty()) {
                    RequestState.Success(characters.slice(range))
                } else {
                    characters = apiService.getCharactersFromComic(charactersUrl.slice(range))
                    RequestState.Success(characters)
                }
            }.getOrElse {
                RequestState.Error("Error getting characters, try again later.")
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