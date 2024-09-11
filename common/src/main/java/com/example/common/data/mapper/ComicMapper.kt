package com.example.common.data.mapper

import com.example.common.data.model.ComicsDataResponse
import com.example.common.domain.model.Comic

fun ComicsDataResponse.toComicList(): List<Comic> {
    val comicsList = mutableListOf<Comic>()
    this.container.comics.forEach { comicResponse ->
        val comic = Comic(
            title = comicResponse.title,
            series = comicResponse.series.name,
            thumbnail = comicResponse.thumbnail.path.plus(comicResponse.thumbnail.extension),
            charactersAvailable = comicResponse.characters.available,
            charactersUrl = comicResponse.characters.charactersList.map { it.characterUrl }
        )
        comicsList.add(comic)
    }
    return comicsList
}