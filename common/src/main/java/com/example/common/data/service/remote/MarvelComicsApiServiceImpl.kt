package com.example.common.data.service.remote

import com.example.common.BuildConfig
import com.example.common.data.mapper.toCharacter
import com.example.common.data.mapper.toComicList
import com.example.common.data.model.CharacterDataResponse
import com.example.common.data.model.ComicsDataResponse
import com.example.common.data.model.RequestState
import com.example.common.data.util.Consts.API_KEY
import com.example.common.data.util.Consts.BASE_URL
import com.example.common.data.util.Consts.CHARACTERS
import com.example.common.data.util.Consts.CHARACTERS_ID
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

class MarvelComicsApiServiceImpl(
    private val httpClient: HttpClient
) : MarvelComicsApiService {
    override suspend fun getComics(): RequestState<List<Comic>> {
        return runCatching {
            val url = BASE_URL.plus(COMICS)
            val request = insertParameters(url)

            val response = request.body<ComicsDataResponse>()

            RequestState.Success(response.toComicList())
        }.getOrElse {
            RequestState.Error("")
        }
    }

    override suspend fun getCharactersFromComic(charactersUrl: List<String>): RequestState<List<Character>> {
        return runCatching {
            val url = BASE_URL.plus(CHARACTERS)
            val charactersList = mutableListOf<Character>()
            charactersUrl.forEach { characterUrl ->
                val request = insertParameters(
                    url = url,
                    parameter = Parameter(
                        key = CHARACTERS_ID,
                        value = characterUrl
                    )
                )
                val response = request.body<CharacterDataResponse>()
                charactersList.add(response.toCharacter())
            }

            RequestState.Success(charactersList)
        }.getOrElse {
            RequestState.Error("")
        }
    }

    private suspend fun insertParameters(
        url: String,
        parameter: Parameter? = null
    ): HttpResponse {
        val timestamp = System.currentTimeMillis().toString()
        val privateKey = BuildConfig.PRIVATE_API_KEY
        val publicKey = BuildConfig.PUBLIC_API_KEY
        val hash = generateMd5(timestamp, privateKey, publicKey)

        val request = httpClient.get(url) {
            parameter(TIMESTAMP, timestamp)
            parameter(API_KEY, publicKey)
            parameter(HASH, hash)
            if (parameter != null) {
                parameter(parameter.key, parameter.value)
            }
        }

        return request
    }

    data class Parameter(
        val key: String,
        val value: String
    )
}