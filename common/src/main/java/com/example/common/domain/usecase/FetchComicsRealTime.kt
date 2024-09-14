package com.example.common.domain.usecase

import com.example.common.domain.model.Comic
import kotlinx.coroutines.flow.Flow

interface FetchComicsRealTime {
    suspend operator fun invoke(): Flow<List<Comic>>
}