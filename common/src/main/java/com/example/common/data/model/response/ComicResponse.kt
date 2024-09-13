package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("title")
    val title: String,

    @SerialName("series")
    val series: SeriesSummary,

    @SerialName("thumbnail")
    val thumbnail: Image,

    @SerialName("characters")
    val characters: ComicCharactersList
)
