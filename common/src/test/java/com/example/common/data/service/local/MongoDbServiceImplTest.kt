package com.example.common.data.service.local

import com.example.common.data.mapper.toComic
import com.example.common.data.model.dto.CharacterDto
import com.example.common.data.model.dto.ComicDto
import com.example.common.domain.service.local.MongoDbService
import com.example.common.util.Mocks
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration
import io.realm.kotlin.ext.query
import kotlinx.coroutines.test.runTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import kotlin.test.assertEquals

class MongoDbServiceImplTest {

    private lateinit var realm: Realm
    private lateinit var mongoDbService: MongoDbService

    @Before
    fun setUp() {
        val config = RealmConfiguration.Builder(
            schema = setOf(
                ComicDto::class,
                CharacterDto::class
            )
        ).inMemory().name("realm_test").build()

        realm = Realm.open(config)
        mongoDbService = MongoDbServiceImpl(realm)
    }

    @After
    fun tearDown() {
        realm.close()
    }

    @Test
    fun `GIVEN comic and characters list WHEN call THEN should save comic on database`() = runTest {
        val comic = Mocks.comic
        val characterList = listOf(Mocks.character)

        mongoDbService.saveComic(comic, characterList)

        val localComics = realm.query<ComicDto>().find().toList().map { it.toComic() }

        assert(localComics.contains(comic))
        assertEquals(1, localComics.size)
    }

    @Test
    fun `GIVEN comicId WHEN call THEN should get all characters from database`() = runTest {
        val comic = Mocks.comic
        val character = Mocks.character
        val characterList = listOf(character, character)

        mongoDbService.saveComic(comic, characterList)

        val localCharacters = mongoDbService.getCharactersFromComic(comic.id)

        assertEquals(characterList, localCharacters)
    }

    @Test
    fun `GIVEN comic WHEN call THEN should delete comic of the database`() = runTest {
        val comic = Mocks.comic
        val comic2 = Mocks.comic2
        val characterList = listOf(Mocks.character)

        mongoDbService.saveComic(comic, characterList)
        mongoDbService.saveComic(comic2, characterList)

        mongoDbService.deleteComic(comic2)
        val localComics = realm.query<ComicDto>().find().toList().map { it.toComic() }

        assert(localComics.contains(comic))
        assert(!localComics.contains(comic2))
        assertEquals(1, localComics.size)
    }
}