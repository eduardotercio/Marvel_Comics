package com.example.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val title: String,

    val series: String,

    val thumbnail: String,

    val charactersAvailable: Int,

    val charactersUrl: List<String>,

    val isFavorite: Boolean = false
)
