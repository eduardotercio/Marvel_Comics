package com.example.common.domain.usecase

import com.example.common.domain.model.Comic

interface DeleteComicUseCase {

    suspend operator fun invoke(comic: Comic)
}