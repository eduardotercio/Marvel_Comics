package com.example.common.domain.usecase

import com.example.common.domain.model.Comic
import com.example.common.domain.repository.ComicsRepository
import kotlinx.coroutines.flow.Flow

class FetchComicsRealTimeImpl(
    private val repository: ComicsRepository
) : FetchComicsRealTime {
    override suspend fun invoke(): Flow<List<Comic>> {
        return repository.fetchComicsRealTime()
    }
}
