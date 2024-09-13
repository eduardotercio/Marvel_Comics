package com.example.common.domain.model

import kotlinx.serialization.Serializable

@Serializable
data class Comic(
    val id: Int,

    val title: String,

    val series: String,

    val imageUrl: String,

    val charactersAvailable: Int,

    val charactersUrl: List<String>,

    val isFavorite: Boolean = false
)
