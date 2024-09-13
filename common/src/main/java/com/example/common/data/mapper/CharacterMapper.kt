package com.example.common.data.mapper

import com.example.common.data.model.dto.CharacterDto
import com.example.common.data.model.response.CharacterDataResponse
import com.example.common.domain.model.Character

fun CharacterDataResponse.toCharacter(): Character {
    val characterResponse = this.container.characterResponses[0]
    val httpsImage = "https".plus(characterResponse.thumbnail.path.drop(4))
    return Character(
        id = characterResponse.id,
        name = characterResponse.name,
        imageUrl = httpsImage.plus('.').plus(characterResponse.thumbnail.extension),
        characterUrl = characterResponse.characterUrl
    )
}

fun Character.toCharacterDto(): CharacterDto {
    return CharacterDto(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        characterUrl = this.characterUrl
    )
}

fun CharacterDto.toCharacter(): Character {
    return Character(
        id = this.id,
        name = this.name,
        imageUrl = this.imageUrl,
        characterUrl = this.characterUrl
    )
}