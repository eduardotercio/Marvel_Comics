package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class CharacterDataResponse(
    @SerialName("code")
    val code: Int,

    @SerialName("data")
    val container: CharacterContainer
)
