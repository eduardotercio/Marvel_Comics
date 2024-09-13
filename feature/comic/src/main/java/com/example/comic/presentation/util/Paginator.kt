package com.example.comic.presentation.util

interface Paginator<Key, Item> {
    suspend fun loadNextItems()
    fun reset()
}