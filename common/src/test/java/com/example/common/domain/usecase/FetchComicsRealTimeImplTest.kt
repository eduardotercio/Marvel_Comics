package com.example.common.domain.usecase

import app.cash.turbine.test
import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.util.Mocks
import io.mockk.coEvery
import io.mockk.mockk
import kotlinx.coroutines.flow.flowOf
import kotlinx.coroutines.test.runTest
import org.junit.Test

class FetchComicsRealTimeImplTest {

    private val repository = mockk<ComicsRepository>()
    private val useCase = FetchComicsRealTimeImpl(repository)

    @Test
    fun `WHEN repository returns flow of comics THEN useCase should return a flow of comics that can be collectable`() =
        runTest {
            val initialItems = listOf(Mocks.comic)
            val updatedItems = listOf(Mocks.comic, Mocks.comic)
            coEvery { repository.fetchLocalComicsRealTime() } returns flowOf(
                initialItems,
                updatedItems
            )

            useCase.invoke().test {
                assert(awaitItem() == initialItems)

                assert(awaitItem() == updatedItems)

                awaitComplete()
            }
        }
}