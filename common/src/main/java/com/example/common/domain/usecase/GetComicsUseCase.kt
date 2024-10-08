package com.example.common.domain.usecase

import com.example.common.data.util.RequestState
import com.example.common.domain.model.Comic

interface GetComicsUseCase {
    suspend operator fun invoke(): RequestState<List<Comic>>
}