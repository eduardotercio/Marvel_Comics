package com.example.common.domain.di

import com.example.common.domain.usecase.DeleteComicUseCase
import com.example.common.domain.usecase.DeleteComicUseCaseImpl
import com.example.common.domain.usecase.GetAllCharactersFromComicUseCase
import com.example.common.domain.usecase.GetAllCharactersFromComicUseCaseImpl
import com.example.common.domain.usecase.GetCharactersPaginationUseCase
import com.example.common.domain.usecase.GetCharactersPaginationUseCaseImpl
import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.domain.usecase.GetComicsUseCaseImpl
import com.example.common.domain.usecase.SaveComicUseCase
import com.example.common.domain.usecase.SaveComicUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDomainModule = module {
    factoryOf(::GetComicsUseCaseImpl) bind GetComicsUseCase::class
    factoryOf(::GetCharactersPaginationUseCaseImpl) bind GetCharactersPaginationUseCase::class
    factoryOf(::SaveComicUseCaseImpl) bind SaveComicUseCase::class
    factoryOf(::GetAllCharactersFromComicUseCaseImpl) bind GetAllCharactersFromComicUseCase::class
    factoryOf(::DeleteComicUseCaseImpl) bind DeleteComicUseCase::class
}