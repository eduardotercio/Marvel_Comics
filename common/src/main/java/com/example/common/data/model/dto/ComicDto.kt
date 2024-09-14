package com.example.common.data.model.dto

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class ComicDto : RealmObject {
    @PrimaryKey
    var id: Int = 0

    var title: String = ""

    var series: String = ""

    var imageUrl: String = ""

    var charactersAvailable: Int = 0

    var charactersList: RealmList<CharacterDto> = realmListOf()

    var isFavorite: Boolean = false
}
