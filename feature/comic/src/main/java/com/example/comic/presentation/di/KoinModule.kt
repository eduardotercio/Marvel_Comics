package com.example.comic.presentation.di

import com.example.comic.presentation.screen.ComicScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val comicPresentationModule = module {
    viewModelOf(::ComicScreenViewModel)
}