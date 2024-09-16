package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.repository.CharacterRepository
import com.example.common.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetAllCharactersFromComicUseCaseImplTest {

    private val repository = mockk<CharacterRepository>()
    private val useCase = GetAllCharactersFromComicUseCaseImpl(repository)

    @Test
    fun `GIVEN list of characterUrl WHEN repository returns Success characters THEN useCase should return the same Success characters`() =
        runTest {
            val charactersUrl = Mocks.characterUrlList
            val expectedResponse =
                RequestState.Success(listOf(Mocks.character))

            coEvery { repository.getAllCharactersFromComic(charactersUrl) } returns expectedResponse
            val actualResponse = useCase.invoke(charactersUrl)

            assertEquals(expectedResponse, actualResponse)
            assertEquals(expectedResponse, actualResponse)
            assertEquals(charactersUrl.size, (actualResponse as RequestState.Success).data.size)
            coVerify { repository.getAllCharactersFromComic(charactersUrl) }
        }

    @Test
    fun `GIVEN list of characterUrl WHEN repository returns Error THEN useCase should return the same Error`() =
        runTest {
            val charactersUrl = Mocks.characterUrlList
            val expectedResponse = RequestState.Error(Mocks.errorMessage)

            coEvery { repository.getAllCharactersFromComic(charactersUrl) } returns expectedResponse
            val actualResponse = useCase.invoke(charactersUrl)

            assertEquals(expectedResponse, actualResponse)
            coVerify { repository.getAllCharactersFromComic(charactersUrl) }
        }
}