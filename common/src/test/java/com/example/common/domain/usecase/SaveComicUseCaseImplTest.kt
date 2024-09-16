package com.example.common.domain.usecase

import com.example.common.domain.repository.ComicsRepository
import com.example.common.util.Mocks
import io.mockk.coVerify
import io.mockk.mockk
import kotlinx.coroutines.test.runTest
import org.junit.Test

class SaveComicUseCaseImplTest {

    private val repository = mockk<ComicsRepository>(relaxed = true)
    private val useCase = SaveComicUseCaseImpl(repository)

    @Test
    fun `GIVEN comic and list of character WHEN call useCase using them THEN repository should use them too`() =
        runTest {
            val comic = Mocks.comic
            val characters = listOf(Mocks.character)

            useCase.invoke(comic, characters)

            coVerify { repository.saveComic(comic, characters) }
        }
}