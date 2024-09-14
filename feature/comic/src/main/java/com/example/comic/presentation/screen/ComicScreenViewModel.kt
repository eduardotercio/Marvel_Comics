package com.example.comic.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.comic.presentation.util.DefaultPaginator
import com.example.common.data.util.RequestState
import com.example.common.domain.model.Character
import com.example.common.domain.usecase.GetCharactersPaginationUseCaseImpl
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.launch

class ComicScreenViewModel(
    private val getCharactersFromComicUseCase: GetCharactersPaginationUseCaseImpl
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
        onRequest = { nextPage, charactersUrl, comicId ->
            fetchCharacters(
                nextPage,
                PAGE_SIZE,
                charactersUrl,
                comicId
            )
        },
        getNextKey = {
            currentState.page + ONE
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
                is ComicScreenContract.Event.LoadNextItems -> {
                    paginator.loadNextItems(event.charactersUrl, event.comicId)
                }
            }
        }
    }

    private suspend fun fetchCharacters(
        page: Int,
        pageSize: Int,
        charactersUrl: List<String>,
        comicId: Int
    ): RequestState<List<Character>> {
        return getCharactersFromComicUseCase(page, pageSize, charactersUrl, comicId)
    }

    private companion object {
        const val PAGE_SIZE = 8
        const val ONE = 1
    }
}