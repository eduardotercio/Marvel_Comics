package com.example.common.data.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService

class CharacterRepositoryImpl(
    private val apiService: MarvelComicsApiService,
    private val mongoDbService: MongoDbService
) : CharacterRepository {
    override suspend fun getCharactersFromComic(
        charactersUrl: List<String>,
        comicId: Int,
        range: IntRange
    ): RequestState<List<Character>> {
        val response = apiService.getCharactersFromComic(charactersUrl.slice(range))
        return response
    }
}