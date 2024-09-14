package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterResponse(
    @SerialName("id")
    val id: Int,

    @SerialName("name")
    val name: String,

    @SerialName("thumbnail")
    val thumbnail: Image,

    @SerialName("resourceURI")
    val characterUrl: String
)
