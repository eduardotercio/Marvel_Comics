package com.example.common.data.model.dto

import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey

internal class CharacterDto : RealmObject {
    @PrimaryKey
    var id: Int = 0

    var name: String = ""

    var imageUrl: String = ""

    var characterUrl: String = ""
}
