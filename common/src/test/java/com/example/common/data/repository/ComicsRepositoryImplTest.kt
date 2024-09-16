package com.example.common.data.repository

import app.cash.turbine.test
import com.example.common.data.util.RequestState
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService
import com.example.common.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class ComicsRepositoryImplTest {

    private lateinit var apiService: MarvelComicsApiService
    private lateinit var mongoDbService: MongoDbService
    private lateinit var repository: ComicsRepository

    @Before
    fun setUp() {
        apiService = mockk<MarvelComicsApiService>()
        mongoDbService = mockk<MongoDbService>()
        repository = ComicsRepositoryImpl(apiService, mongoDbService)
    }

    @Test
    fun `WHEN apiService returns comics THEN repository should return Success comics`() =
        runTest {
            val expectedResponse = listOf(Mocks.comic)
            coEvery { apiService.getComics() } returns expectedResponse

            val actualResponse = repository.getComicsFromApi()

            assertEquals(expectedResponse, (actualResponse as RequestState.Success).data)
            coVerify { apiService.getComics() }
        }

    @Test
    fun `WHEN apiService throws an Error THEN repository should return Error`() =
        runTest {
            coEvery { apiService.getComics() } throws Throwable()

            val actualResponse = repository.getComicsFromApi()
            val expectedResponse = actualResponse as RequestState.Error

            assertEquals(expectedResponse, actualResponse)
            coVerify { apiService.getComics() }
        }

    @Test
    fun `WHEN mongoDbService returns flow of comics THEN repository should return a collectable flow of comics`() =
        runTest {
            val initialItems = listOf(Mocks.comic)
            val updatedItems = listOf(Mocks.comic, Mocks.comic)
            coEvery { mongoDbService.fetchComicsRealTime() } returns flowOf(
                initialItems,
                updatedItems
            )

            repository.fetchLocalComicsRealTime().test {
                assert(awaitItem() == initialItems)

                assert(awaitItem() == updatedItems)

                awaitComplete()
            }

            coVerify { mongoDbService.fetchComicsRealTime() }
        }

    @Test
    fun `GIVEN comic and list of character WHEN call repository using them THEN mongoDbService should use them too`() =
        runTest {
            val comic = Mocks.comic
            val characters = listOf(Mocks.character)

            repository.saveComic(comic, characters)

            coVerify { mongoDbService.saveComic(comic, characters) }
        }

    @Test
    fun `GIVEN comic WHEN call repository with it THEN mongoDbService should use them too`() =
        runTest {
            val comic = Mocks.comic

            repository.deleteComic(comic)

            coVerify { mongoDbService.deleteComic(comic) }
        }

}