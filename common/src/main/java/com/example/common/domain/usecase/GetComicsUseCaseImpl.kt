package com.example.common.domain.usecase

import com.example.common.data.model.RequestState
import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository

class GetComicsUseCaseImpl(
    private val repository: ComicsRepository
) : GetComicsUseCase {
    override suspend fun invoke(onlyLocal: Boolean): RequestState<List<Comic>> {
        return repository.getComics(onlyLocal)
    }
}