package com.example.home.presentation.screen

import com.example.common.domain.model.Comic
import com.example.common.presentation.base.UiEffect
import com.example.common.presentation.base.UiEvent
import com.example.common.presentation.base.UiState

object HomeScreenContract {
    interface Event : UiEvent {
        data object FetchComics : Event
    }

    interface Effect : UiEffect {
        data object SnackbarErrorFindingComics: Effect
    }

    data class State(
        val comics: List<Comic> = listOf()
    ) : UiState
}