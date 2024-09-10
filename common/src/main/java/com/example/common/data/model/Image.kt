package com.example.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class Image(
    @SerialName("path")
    val path: String,

    @SerialName("extension")
    val extension: String
)
