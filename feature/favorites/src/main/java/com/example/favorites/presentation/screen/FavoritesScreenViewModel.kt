package com.example.favorites.presentation.screen

import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.presentation.base.BaseViewModel

class FavoritesScreenViewModel(
    private val getComicsUseCase: GetComicsUseCase,
) :
    BaseViewModel<FavoritesScreenContract.Event, FavoritesScreenContract.State, FavoritesScreenContract.Effect>() {
    override fun setInitialState() = FavoritesScreenContract.State()

    override fun handleEvents(event: FavoritesScreenContract.Event) {

    }
}