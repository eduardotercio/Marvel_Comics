package com.example.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicsDataResponse(
    @SerialName("code")
    val code: Int,

    @SerialName("data")
    val container: ComicContainer
)
