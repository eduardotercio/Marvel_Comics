package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.repository.ComicsRepository
import com.example.common.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetComicsUseCaseImplTest {

    private val repository = mockk<ComicsRepository>()
    private val useCase = GetComicsUseCaseImpl(repository)

    @Test
    fun `WHEN repository returns Success comics THEN useCase should return the same Success comics`() =
        runTest {
            val expectedResponse = RequestState.Success(listOf(Mocks.comic))
            coEvery { repository.getComicsFromApi() } returns expectedResponse

            val actualResponse = useCase.invoke()

            assertEquals(expectedResponse, actualResponse)
            coVerify { repository.getComicsFromApi() }
        }

    @Test
    fun `WHEN repository returns Error THEN useCase should return the same Error`() =
        runTest {
            val expectedResponse = RequestState.Error(Mocks.errorMessage)

            coEvery { repository.getComicsFromApi() } returns expectedResponse
            val actualResponse = useCase.invoke()

            assertEquals(expectedResponse, actualResponse)
            coVerify { repository.getComicsFromApi() }
        }
}