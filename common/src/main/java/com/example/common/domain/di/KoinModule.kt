package com.example.common.domain.di

import com.example.common.domain.usecase.GetCharacterUseCase
import com.example.common.domain.usecase.GetCharacterUseCaseImpl
import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.domain.usecase.GetComicsUseCaseImpl
import org.koin.core.module.dsl.factoryOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDomainModule = module {
    factoryOf(::GetComicsUseCaseImpl) bind GetComicsUseCase::class
    factoryOf(::GetCharacterUseCaseImpl) bind GetCharacterUseCase::class
}