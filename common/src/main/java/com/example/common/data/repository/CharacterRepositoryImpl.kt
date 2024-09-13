package com.example.common.data.repository

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.service.remote.MarvelComicsApiService
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CharacterRepositoryImpl(
    private val apiService: MarvelComicsApiService
) : CharacterRepository {
    override suspend fun getCharactersFromComic(charactersUrl: List<String>): RequestState<List<Character>> {
        return withContext(Dispatchers.IO) {
            apiService.getCharactersFromComic(charactersUrl)
        }
    }
}