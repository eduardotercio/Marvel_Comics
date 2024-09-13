package com.example.home.presentation.di

import com.example.home.presentation.screen.HomeScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val homePresentationModule = module {
    viewModelOf(::HomeScreenViewModel)
}