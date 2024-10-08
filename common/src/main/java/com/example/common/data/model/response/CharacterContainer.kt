package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterContainer(
    @SerialName("results")
    val characterResponses: Array<CharacterResponse>
)
