package com.example.common.data.repository

import com.example.common.data.model.ComicDataResponse
import com.example.common.data.util.Consts.BASE_URL
import com.example.common.data.util.Consts.COMICS
import com.example.common.domain.repository.ComicsRepository
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

class ComicsRepositoryImpl(
    private val httpClient: HttpClient
) : ComicsRepository {
    override suspend fun getComics() {
        val url = BASE_URL.plus(COMICS)

        val request = httpClient.get(url)

        val response = request.body<ComicDataResponse>()

        println("dudu ->  $response")
    }
}