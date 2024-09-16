package com.example.common.data.service.local

import com.example.common.data.mapper.toCharacter
import com.example.common.data.mapper.toComic
import com.example.common.data.mapper.toComicDto
import com.example.common.data.model.dto.ComicDto
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.service.local.MongoDbService
import io.realm.kotlin.Realm
import io.realm.kotlin.UpdatePolicy
import io.realm.kotlin.ext.query
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.firstOrNull
import kotlinx.coroutines.flow.map

class MongoDbServiceImpl(
    private val realm: Realm
) : MongoDbService {
    override suspend fun saveComic(comic: Comic, characters: List<Character>) {
        realm.write {
            copyToRealm(comic.toComicDto(characters), UpdatePolicy.ALL)
        }
    }

    override suspend fun fetchComicsRealTime(): Flow<List<Comic>> {
        return realm.query<ComicDto>().asFlow()
            .map { resultsChange -> resultsChange.list.map { it.toComic() } }
    }

    override suspend fun getCharactersFromComic(comicId: Int): List<Character> {
        val comic = realm.query(
            clazz = ComicDto::class,
            query = "id == $0 LIMIT(1)", comicId
        ).find().asFlow().firstOrNull()?.list?.firstOrNull()

        return if (comic == null) {
            emptyList()
        } else {
            val characterList = comic.charactersList.asFlow().firstOrNull()?.list?.map {
                it.toCharacter()
            } ?: emptyList()
            characterList
        }
    }

    override suspend fun deleteComic(comic: Comic) {
        realm.writeBlocking {
            val localComic = this.query(
                clazz = ComicDto::class,
                query = "id == $0 LIMIT(1)", comic.id
            ).first().find()

            localComic?.let { delete(it) }
        }
    }

    override suspend fun cleanDatabase() {
        realm.writeBlocking {
            this.deleteAll()
        }
    }
}