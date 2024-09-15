package com.example.common.domain.usecase

import com.example.common.domain.repository.ComicsRepository
import com.example.common.domain.util.Mocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class DeleteComicUseCaseImplTest {

    private val repository = mockk<ComicsRepository>(relaxed = true)
    private val useCase = DeleteComicUseCaseImpl(repository)

    @Test
    fun `GIVEN comic WHEN call useCase with it THEN repository should use them too`() = runTest {
        val comic = Mocks.comic

        useCase.invoke(comic)

        coVerify { repository.deleteComic(comic) }
    }
}