package com.example.common.data.service.remote

import com.example.common.BuildConfig
import com.example.common.data.mapper.toCharacter
import com.example.common.data.mapper.toComicList
import com.example.common.data.model.response.CharacterDataResponse
import com.example.common.data.model.response.ComicsDataResponse
import com.example.common.data.util.Consts.API_KEY
import com.example.common.data.util.Consts.BASE_URL
import com.example.common.data.util.Consts.COMICS
import com.example.common.data.util.Consts.HASH
import com.example.common.data.util.Consts.TIMESTAMP
import com.example.common.data.util.generateMd5
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.service.remote.MarvelComicsApiService
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.client.statement.HttpResponse
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.withContext

class MarvelComicsApiServiceImpl(
    private val httpClient: HttpClient
) : MarvelComicsApiService {
    override suspend fun getComics(): List<Comic> {
        val url = BASE_URL.plus(COMICS)
        val request = insertDefaultParameters(url)

        val response = request.body<ComicsDataResponse>()

        return response.toComicList()
    }

    override suspend fun getCharactersFromComic(charactersUrl: List<String>): List<Character> {
        return withContext(Dispatchers.IO) {
            val charactersList = charactersUrl.map { url ->
                async {
                    val request = insertDefaultParameters(url = url)
                    val response = request.body<CharacterDataResponse>()
                    response.toCharacter()
                }
            }.awaitAll()

            charactersList
        }
    }

    private suspend fun insertDefaultParameters(
        url: String,
    ): HttpResponse {
        val timestamp = System.currentTimeMillis().toString()
        val privateKey = BuildConfig.PRIVATE_API_KEY
        val publicKey = BuildConfig.PUBLIC_API_KEY
        val hash = generateMd5(timestamp, privateKey, publicKey)

        val request = httpClient.get(url) {
            parameter(TIMESTAMP, timestamp)
            parameter(API_KEY, publicKey)
            parameter(HASH, hash)
        }

        return request
    }
}