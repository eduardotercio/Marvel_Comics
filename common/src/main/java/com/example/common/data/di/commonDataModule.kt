package com.example.common.data.di

import com.example.common.data.repository.ComicsRepositoryImpl
import com.example.common.domain.repository.ComicsRepository
import org.koin.core.module.dsl.singleOf
import org.koin.dsl.bind
import org.koin.dsl.module

val commonDataModule = module {

    singleOf(::ComicsRepositoryImpl) bind ComicsRepository::class
}