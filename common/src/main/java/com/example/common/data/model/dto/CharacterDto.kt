package com.example.common.data.model.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey


data class CharacterDto(
    @PrimaryKey
    var id: Int = 0,

    val name: String = "",

    val imageUrl: String = "",

    val characterUrl: String = ""
) : RealmObject
