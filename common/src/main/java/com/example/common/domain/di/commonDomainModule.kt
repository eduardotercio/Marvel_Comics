package com.example.common.domain.di

import com.example.common.data.service.remote.MarvelComicsApiServiceImpl
import com.example.common.domain.service.remote.MarvelComicsApiService
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDomainModule = module {

    singleOf(::MarvelComicsApiServiceImpl) bind MarvelComicsApiService::class
}