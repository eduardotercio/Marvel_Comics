package com.example.common.data.di

import com.example.common.BuildConfig
import com.example.common.data.util.generateMd5
import io.ktor.client.HttpClient
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.http.parameters
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json
import org.koin.dsl.module

private const val TIME_OUT = 15000L
private const val TIMESTAMP = ""
private const val API_KEY = ""
private const val HASH = ""

val ktorModule = module {
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
            install(DefaultRequest) {
                parameters {
                    val timestamp = System.currentTimeMillis().toString()
                    val privateKey = BuildConfig.PRIVATE_API_KEY
                    val publicKey = BuildConfig.PUBLIC_API_KEY
                    val hash = generateMd5(timestamp, privateKey, publicKey)
                    append(TIMESTAMP, timestamp)
                    append(API_KEY, publicKey)
                    append(HASH, hash)
                }
            }
        }
    }
}