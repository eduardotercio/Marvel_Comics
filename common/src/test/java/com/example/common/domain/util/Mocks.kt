package com.example.common.domain.util

import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic

object Mocks {
    val comic = Comic(
        id = 1,
        title = "Miranha",
        series = "Multiverso",
        imageUrl = "",
        charactersAvailable = 3,
        charactersUrl = listOf(""),
        isFavorite = false
    )

    val character = Character(
        id = 1,
        name = "Iron man",
        imageUrl = "",
        characterUrl = ""
    )

    val errorMessage = "Error at getting data"

    val characterUrlList =  listOf(
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl",
        "characterUrl"
    )
}