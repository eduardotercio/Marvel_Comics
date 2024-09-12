package com.example.common.data.mapper

import com.example.common.data.model.ComicsDataResponse
import com.example.common.domain.model.Comic

fun ComicsDataResponse.toComicList(): List<Comic> {
    val comicsList = mutableListOf<Comic>()
    this.container.comics.forEach { comicResponse ->
        val httpsImage = "https".plus(comicResponse.thumbnail.path.drop(4))
        val comic = Comic(
            id = comicResponse.id,
            title = comicResponse.title,
            series = comicResponse.series.name,
            thumbnail = httpsImage.plus('.').plus(comicResponse.thumbnail.extension),
            charactersAvailable = comicResponse.characters.available,
            charactersUrl = comicResponse.characters.charactersList.map { it.characterUrl }
        )
        comicsList.add(comic)
    }
    return comicsList
}