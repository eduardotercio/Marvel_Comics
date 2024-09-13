package com.example.comic.presentation.screen

import com.example.common.domain.model.Character
import com.example.common.presentation.base.UiEffect
import com.example.common.presentation.base.UiEvent
import com.example.common.presentation.base.UiState

object ComicScreenContract {
    interface Event : UiEvent {
        data class SaveCharactersUrl(
            val charactersUrl: List<String>
        ) : Event

        data object LoadNextItems : Event
    }

    interface Effect : UiEffect {
        data object SnackbarErrorFindingComics : Effect
    }

    data class State(
        val charactersUrl: List<String> = listOf(),
        val characters: List<Character> = listOf(),
        val isLoading: Boolean = false,
        val endReached: Boolean = false,
        val page: Int = 0
    ) : UiState
}