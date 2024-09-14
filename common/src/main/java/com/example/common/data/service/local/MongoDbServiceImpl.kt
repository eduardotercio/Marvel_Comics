package com.example.common.data.service.local

import com.example.common.data.mapper.toCharacter
import com.example.common.data.mapper.toComic
import com.example.common.data.mapper.toComicDto
import com.example.common.data.model.RequestState
import com.example.common.data.model.dto.ComicDto
import com.example.common.domain.model.Character
import com.example.common.domain.model.Comic
import com.example.common.domain.service.local.MongoDbService
import io.realm.kotlin.Realm
import kotlinx.coroutines.flow.firstOrNull

class MongoDbServiceImpl(
    private val realm: Realm
) : MongoDbService {
    override suspend fun saveComic(comic: Comic, characters: List<Character>) {
        realm.write {
            copyToRealm(comic.toComicDto(characters))
        }
    }

    override suspend fun getComics(): RequestState<List<Comic>> {
        return runCatching {
            val comics = realm.query(
                clazz = ComicDto::class,
            ).find().asFlow().firstOrNull()?.list?.map {
                it.toComic()
            }

            RequestState.Success(comics ?: emptyList())
        }.getOrElse {
            RequestState.Error("Error getting comics, try again later.")
        }
    }

    override suspend fun getCharactersFromComic(comicId: Int): RequestState<List<Character>> {
        return runCatching {
            val comic = realm.query(
                clazz = ComicDto::class,
                query = "id == $0 LIMIT(1)", comicId
            ).find().asFlow().firstOrNull()?.list?.firstOrNull()

            if (comic == null) {
                RequestState.Success(emptyList())
            } else {
                val characterList = comic.charactersList.asFlow().firstOrNull()?.list?.map {
                    it.toCharacter()
                }
                RequestState.Success(characterList ?: emptyList())
            }
        }.getOrElse {
            RequestState.Error("Error getting characters, try again later.")
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
}