package com.example.common.data.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class SeriesSummary(
    @SerialName("name")
    val name: String
)
