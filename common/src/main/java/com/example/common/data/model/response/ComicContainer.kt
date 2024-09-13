package com.example.common.data.model.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ComicContainer(
    @SerialName("results")
    val comics: Array<ComicResponse>
)
