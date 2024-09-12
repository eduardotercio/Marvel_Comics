package com.example.common.presentation.model

import kotlinx.serialization.Serializable

@Serializable
sealed class Route {
    @Serializable
    data object Splash : Route()

    @Serializable
    data object Home : Route()

    @Serializable
    data object Favorite : Route()

    @Serializable
    data class Comic(
        val characterUrl: String
    ) : Route()
}