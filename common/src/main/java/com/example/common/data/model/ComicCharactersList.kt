package com.example.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicCharactersList(
    @SerialName("available")
    val available: Int,

    @SerialName("items")
    val charactersList: Array<ComicCharactersData>,
)
