package com.example.hqsmarvel

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.common.BuildConfig
import com.example.common.data.model.ComicDataResponse
import com.example.common.data.util.Consts.BASE_URL
import com.example.common.data.util.Consts.COMICS
import com.example.common.data.util.generateMd5
import com.example.hqsmarvel.ui.theme.HQsMarvelTheme
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.plugins.DefaultRequest
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.request.get
import io.ktor.client.request.parameter
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            HQsMarvelTheme {
                LaunchedEffect(key1 = Unit) {
                    test()
                }
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    Greeting(
                        name = "Android",
                        modifier = Modifier.padding(innerPadding)
                    )
                }
            }
        }
    }

    private suspend fun test() {
        val httpClient = HttpClient {
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
        val url = BASE_URL.plus(COMICS)
        val timestamp = System.currentTimeMillis().toString()
        val privateKey = BuildConfig.PRIVATE_API_KEY
        val publicKey = BuildConfig.PUBLIC_API_KEY
        val hash = generateMd5(timestamp, privateKey, publicKey)
        val request = httpClient.get(url) {
            parameter(TIMESTAMP, timestamp)
            parameter(API_KEY, publicKey)
            parameter(HASH, hash)
        }

        val response = request.body<ComicDataResponse>()

        println("dudu ->  $response")
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    HQsMarvelTheme {
        Greeting("Android")
    }
}

private const val TIME_OUT = 15000L
private const val TIMESTAMP = "ts"
private const val API_KEY = "apikey"
private const val HASH = "hash"