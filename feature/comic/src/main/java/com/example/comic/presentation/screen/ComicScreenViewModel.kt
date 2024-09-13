package com.example.comic.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.comic.presentation.util.DefaultPaginator
import com.example.common.data.model.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.usecase.GetCharacterUseCaseImpl
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ComicScreenViewModel(
    private val getCharactersFromComicUseCase: GetCharacterUseCaseImpl
) :
    BaseViewModel<ComicScreenContract.Event, ComicScreenContract.State, ComicScreenContract.Effect>() {
    override fun setInitialState() = ComicScreenContract.State()

    private val paginator = DefaultPaginator(
        initialKey = currentState.page,
        onLoadUpdated = {
            setState {
                copy(isLoading = it)
            }
        },
        onRequest = { nextPage ->
            fetchCharacters(nextPage, 8)
        },
        getNextKey = {
            currentState.page + 1
        },
        onSuccess = { items, newKey ->
            setState {
                copy(
                    characters = currentState.characters + items,
                    page = newKey,
                    endReached = items.isEmpty()
                )
            }
        },
        onError = {
            setEffect {
                ComicScreenContract.Effect.SnackbarErrorFindingComics
            }
        }
    )

    override fun handleEvents(event: ComicScreenContract.Event) {
        viewModelScope.launch {
            when (event) {
                is ComicScreenContract.Event.SaveCharactersUrl -> {
                    setState { copy(charactersUrl = event.charactersUrl) }
                    loadNextItems()
                }

                is ComicScreenContract.Event.LoadNextItems -> {
                    loadNextItems()
                }
            }
        }
    }

    private fun loadNextItems() {
        viewModelScope.launch {
            paginator.loadNextItems()
        }
    }

    private suspend fun fetchCharacters(page: Int, pageSize: Int): RequestState<List<Character>> {
        return getCharactersFromComicUseCase(currentState.charactersUrl, page, pageSize)
    }
}