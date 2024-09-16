package com.example.common.data.repository

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.service.local.MongoDbService
import com.example.common.domain.service.remote.MarvelComicsApiService
import com.example.common.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Before
import org.junit.Test

class CharacterRepositoryImplTest {

    private lateinit var apiService: MarvelComicsApiService
    private lateinit var mongoDbService: MongoDbService
    private lateinit var repository: CharacterRepository

    @Before
    fun setUp() {
        apiService = mockk<MarvelComicsApiService>()
        mongoDbService = mockk<MongoDbService>(relaxed = true)
        repository = CharacterRepositoryImpl(apiService, mongoDbService)
    }

    @Test
    fun `GIVEN url list, comicId and range WHEN mongoDbService returns not empty list THEN repository should return Success`() =
        runTest {
            val charactersUrl = Mocks.characterUrlListPagination
            val comicId = 0
            val range = IntRange(8, 9)

            val expectedResponse = Mocks.characterListPagination
            coEvery { mongoDbService.getCharactersFromComic(comicId) } returns expectedResponse

            val actualResponse = repository.getCharactersPagination(charactersUrl, comicId, range)

            assertEquals(expectedResponse.slice(range), (actualResponse as RequestState.Success).data)
            assertEquals(expectedResponse.slice(range).size, actualResponse.data.size)
            coVerify { mongoDbService.getCharactersFromComic(comicId) }
        }

    @Test
    fun `GIVEN url list, comicId and range WHEN mongoDbService returns empty list or Error and apiService returns a list THEN repository should return Success`() =
        runTest {
            val charactersUrl = Mocks.characterUrlListPagination
            val comicId = 0
            val range = IntRange(8, 9)

            val mongoDbExpectedResponse = emptyList<Character>()
            val apiExpectedResponse = listOf(Mocks.character, Mocks.character)
            coEvery { mongoDbService.getCharactersFromComic(comicId) } returns mongoDbExpectedResponse
            coEvery { apiService.getCharactersFromComic(charactersUrl.slice(range)) } returns apiExpectedResponse

            val actualResponse = repository.getCharactersPagination(charactersUrl, comicId, range)

            assertEquals(apiExpectedResponse, (actualResponse as RequestState.Success).data)
            assertEquals(apiExpectedResponse.size, actualResponse.data.size)
            coVerify { mongoDbService.getCharactersFromComic(comicId) }
            coVerify { apiService.getCharactersFromComic(charactersUrl.slice(range)) }
        }

    @Test
    fun `GIVEN page, pageSize, list of Url and comicId WHEN apiService returns Error THEN repository should return Error`() =
        runTest {
            val charactersUrl = Mocks.characterUrlListPagination
            val comicId = 0
            val range = IntRange(8, 9)

            coEvery { apiService.getCharactersFromComic(charactersUrl.slice(range)) } throws Throwable()

            val actualResponse = repository.getCharactersPagination(charactersUrl, comicId, range)
            val expectedResponse = actualResponse as RequestState.Error

            assertEquals(expectedResponse, actualResponse)
            coVerify { mongoDbService.getCharactersFromComic(comicId) }
            coVerify { apiService.getCharactersFromComic(charactersUrl.slice(range)) }
        }

    @Test
    fun `GIVEN list of characterUrl WHEN apiService returns a list of characters THEN repository should return Success`() =
        runTest {
            val charactersUrl = Mocks.characterUrlList

            val expectedResponse = listOf(Mocks.character)
            coEvery { apiService.getCharactersFromComic(charactersUrl) } returns expectedResponse

            val actualResponse = repository.getAllCharactersFromComic(charactersUrl)

            assertEquals(expectedResponse, (actualResponse as RequestState.Success).data)
            assertEquals(charactersUrl.size, actualResponse.data.size)
            coVerify { apiService.getCharactersFromComic(charactersUrl) }
        }

    @Test
    fun `GIVEN list of characterUrl WHEN apiService returns throws error THEN repository should return Error`() =
        runTest {
            val charactersUrl = Mocks.characterUrlList

            coEvery { apiService.getCharactersFromComic(charactersUrl) } throws Throwable()

            val actualResponse = repository.getAllCharactersFromComic(charactersUrl)
            val expectedResponse = actualResponse as RequestState.Error

            assertEquals(expectedResponse, actualResponse)
            coVerify { apiService.getCharactersFromComic(charactersUrl) }
        }

}