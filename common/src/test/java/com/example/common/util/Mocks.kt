package com.example.common.util

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

    val comic2 = Comic(
        id = 2,
        title = "Captain america",
        series = "civil war",
        imageUrl = "",
        charactersAvailable = 7,
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

    val characterUrlList = listOf("characterUrl")

    val characterUrlListPagination = listOf(
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

    val characterListPagination = listOf(
        character,
        character,
        character,
        character,
        character,
        character,
        character,
        character,
        character,
        character
    )
}