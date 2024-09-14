package com.example.comic.presentation.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems(charactersUrl: List<String>, comicId: Int)
    fun reset()
}