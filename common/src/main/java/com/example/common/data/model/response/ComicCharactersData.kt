package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicCharactersData(
    @SerialName("resourceURI")
    val characterUrl: String,

    @SerialName("name")
    val characterName: String,
)
