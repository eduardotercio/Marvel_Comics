package com.example.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val id: Int = 0,

    val title: String = "",

    val series: String = "",

    val imageUrl: String = "",

    val charactersAvailable: Int = 0,

    val charactersUrl: List<String> = listOf(),

    val isFavorite: Boolean = false
)
