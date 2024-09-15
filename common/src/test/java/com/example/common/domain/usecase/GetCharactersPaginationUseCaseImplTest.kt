package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.repository.CharacterRepository
import com.example.common.domain.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetCharactersPaginationUseCaseImplTest {

    private val repository = mockk<CharacterRepository>()
    private val useCase = GetCharactersPaginationUseCaseImpl(repository)

    @Test
    fun `GIVEN page, pageSize, list of Url and comicId WHEN repository returns Success characters THEN useCase should return the same Success characters`() =
        runTest {
            val page = 1
            val pageSize = 8
            val charactersUrl = Mocks.characterUrlList
            val comicId = 0
            val range = IntRange(8, 9)

            val expectedResponse = RequestState.Success(listOf(Mocks.character))
            coEvery {
                repository.getCharactersPagination(
                    charactersUrl,
                    comicId,
                    range
                )
            } returns expectedResponse

            val actualResponse = useCase.invoke(page, pageSize, charactersUrl, comicId)

            assertEquals(expectedResponse, actualResponse)
            coVerify {
                repository.getCharactersPagination(
                    charactersUrl,
                    comicId,
                    range
                )
            }
        }

    @Test
    fun `GIVEN list of characterUrl WHEN repository returns Error THEN useCase should return the same Error`() =
        runTest {
            val page = 1
            val pageSize = 8
            val charactersUrl = Mocks.characterUrlList
            val comicId = 0
            val range = IntRange(8, 9)

            val expectedResponse = RequestState.Error(Mocks.errorMessage)
            coEvery {
                repository.getCharactersPagination(
                    charactersUrl,
                    comicId,
                    range
                )
            } returns expectedResponse

            val actualResponse = useCase.invoke(page, pageSize, charactersUrl, comicId)

            assertEquals(expectedResponse, actualResponse)
            coVerify {
                repository.getCharactersPagination(
                    charactersUrl,
                    comicId,
                    range
                )
            }
        }
}