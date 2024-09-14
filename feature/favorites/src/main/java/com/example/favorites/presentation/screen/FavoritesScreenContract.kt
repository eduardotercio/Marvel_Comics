package com.example.favorites.presentation.screen

import com.example.common.domain.model.Comic
import com.example.common.presentation.base.UiEffect
import com.example.common.presentation.base.UiEvent
import com.example.common.presentation.base.UiState

object FavoritesScreenContract {
    interface Event : UiEvent {
        data class OnComicClicked(
            val charactersUrl: List<String>,
            val comicId: Int
        ) : Event

        data class OnFavoriteIconClicked(
            val charactersUrl: List<String>,
            val comic: Comic
        ) : Event

        data object OnRemoveFavoriteComic : Event
    }

    interface Effect : UiEffect {
        data class NavigateToComicScreen(
            val charactersUrl: List<String>,
            val comicId: Int
        ) : Effect

        data object OpenFavoriteDialog : Effect
    }

    data class State(
        val favoriteComics: List<Comic> = emptyList(),
        val isLoading: Boolean = true,
        val lastComicClickedOnFavorite: Comic = Comic(),
        val charUrlsLastComicClickedOnFavorite: List<String> = listOf()
    ) : UiState
}