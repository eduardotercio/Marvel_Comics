package com.example.favorites.presentation.di

import com.example.favorites.presentation.screen.FavoritesScreenViewModel
import org.koin.androidx.viewmodel.dsl.viewModelOf
import org.koin.dsl.module

val favoritesPresentationModule = module {
    viewModelOf(::FavoritesScreenViewModel)
}