package com.example.comic.presentation.util

import com.example.common.data.model.RequestState

class DefaultPaginator<Key, Item>(
    private val initialKey: Key,
    private inline val onLoadUpdated: (Boolean) -> Unit,
    private inline val onRequest: suspend (nextKey: Key, List<String>, Int) -> RequestState<List<Item>>,
    private inline val getNextKey: suspend (List<Item>) -> Key,
    private inline val onError: suspend (String?) -> Unit,
    private inline val onSuccess: suspend (items: List<Item>, newKey: Key) -> Unit
) : Paginator<Key, Item> {

    private var currentKey = initialKey
    private var isMakingRequest = false

    override suspend fun loadNextItems(
        charactersUrl: List<String>,
        comicId: Int
    ) {
        if (isMakingRequest) {
            return
        }
        isMakingRequest = true
        onLoadUpdated(true)
        val result = onRequest(currentKey, charactersUrl, comicId)
        isMakingRequest = false
        val items = when (result) {
            is RequestState.Success -> {
                result.data
            }

            is RequestState.Error -> {
                onError(result.message)
                onLoadUpdated(false)
                return
            }
        }
        currentKey = getNextKey(items)
        onSuccess(items, currentKey)
        onLoadUpdated(false)
    }

    override fun reset() {
        currentKey = initialKey
    }
}