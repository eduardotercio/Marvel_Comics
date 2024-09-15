package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.util.Mocks
import io.mockk.coEvery
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test
import kotlin.test.assertEquals

class GetComicsUseCaseImplTest {

    private val repository = mockk<ComicsRepository>()
    private val getComics = GetComicsUseCaseImpl(repository)

    @Test
    fun `GIVEN getComicsUseCase WHEN call succeeded THEN return RequestState Success`() = runTest {
        val expectedResponse = RequestState.Success(listOf(Mocks.comic, Mocks.comic, Mocks.comic))

        coEvery { getComics.invoke() } returns expectedResponse
        val actualResponse = getComics.invoke()

        assertEquals(expectedResponse, actualResponse)
        coVerify { getComics.invoke() }
    }

    @Test
    fun `GIVEN getComicsUseCase WHEN call fails THEN return RequestState Error`() = runTest {
        val expectedResponse = RequestState.Error(Mocks.errorMessage)

        coEvery { getComics.invoke() } returns expectedResponse
        val actualResponse = getComics.invoke()

        assertEquals(expectedResponse, actualResponse)
        coVerify { getComics.invoke() }
    }
}