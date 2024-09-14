package com.example.favorites.presentation.screen

import com.example.common.domain.model.Comic
import com.example.common.presentation.base.UiEffect
import com.example.common.presentation.base.UiEvent
import com.example.common.presentation.base.UiState

object FavoritesScreenContract {
    interface Event : UiEvent {
        data object FetchLocalComics : Event
    }

    interface Effect : UiEffect

    data class State(
        val favoriteComics: List<Comic> = emptyList()
    ) : UiState
}