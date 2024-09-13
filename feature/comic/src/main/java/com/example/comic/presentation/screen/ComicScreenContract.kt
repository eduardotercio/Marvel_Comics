package com.example.comic.presentation.screen

import com.example.common.domain.model.Character
import com.example.common.presentation.base.UiEffect
import com.example.common.presentation.base.UiEvent
import com.example.common.presentation.base.UiState

object ComicScreenContract {
    interface Event : UiEvent {
        data class FetchCharactersFromComic(
            val charactersUrl: List<String>
        ) : Event
    }

    interface Effect : UiEffect {
        data object SnackbarErrorFindingComics: Effect
    }

    data class State(
        val characters: List<Character> = listOf()
    ) : UiState
}