package com.example.common.data.di

import com.example.common.data.repository.ComicsRepositoryImpl
import com.example.common.data.service.remote.MarvelComicsApiServiceImpl
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.service.remote.MarvelComicsApiService
import io.ktor.client.HttpClient
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

private const val TIME_OUT = 15000L

val commonDataModule = module {
    single {
        HttpClient {
            install(ContentNegotiation) {
                json(Json {
                    prettyPrint = true
                    isLenient = true
                    ignoreUnknownKeys = true
                })
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIME_OUT
            }
        }
    }

    singleOf(::ComicsRepositoryImpl) bind ComicsRepository::class
    singleOf(::MarvelComicsApiServiceImpl) bind MarvelComicsApiService::class
}