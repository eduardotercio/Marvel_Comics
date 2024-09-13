package com.example.common.data.model.dto

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

data class ComicDto(
    @PrimaryKey
    var id: Int = 0,

    val title: String = "",

    val series: String = "",

    val imageUrl: String = "",

    val charactersAvailable: Int = 0,

    val characters: RealmList<CharacterDto> = realmListOf(),

    val isFavorite: Boolean = false
) : RealmObject
