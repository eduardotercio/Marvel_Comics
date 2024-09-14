package com.example.common.data.mapper

import com.example.common.data.model.dto.ComicDto
import com.example.common.data.model.response.ComicsDataResponse
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import io.realm.kotlin.ext.toRealmList

fun ComicsDataResponse.toComicList(): List<Comic> {
    val comicsList = mutableListOf<Comic>()
    this.container.comics.forEach { comicResponse ->
        val httpsImage = "https".plus(comicResponse.thumbnail.path.drop(4))
        val comic = Comic(
            id = comicResponse.id,
            title = comicResponse.title,
            series = comicResponse.series.name,
            imageUrl = httpsImage.plus('.').plus(comicResponse.thumbnail.extension),
            charactersAvailable = comicResponse.characters.available,
            charactersUrl = comicResponse.characters.charactersList.map { it.characterUrl }
        )
        comicsList.add(comic)
    }
    return comicsList
}

internal fun Comic.toComicDto(characters: List<Character>): ComicDto {
    return ComicDto().apply {
        id = this@toComicDto.id
        title = this@toComicDto.title
        series = this@toComicDto.series
        imageUrl = this@toComicDto.imageUrl
        charactersAvailable = this@toComicDto.charactersAvailable
        charactersList = characters.map { it.toCharacterDto() }.toRealmList()
        isFavorite = this@toComicDto.isFavorite
    }
}

internal fun ComicDto.toComic(): Comic {
    return Comic(
        id = this.id,
        title = this.title,
        series = this.series,
        imageUrl = this.imageUrl,
        charactersAvailable = this.charactersAvailable,
        charactersUrl = this.charactersList.map { it.characterUrl },
        isFavorite = this.isFavorite
    )
}