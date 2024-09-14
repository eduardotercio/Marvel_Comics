package com.example.favorites.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.common.domain.model.Comic
import com.example.common.domain.usecase.DeleteComicUseCase
import com.example.common.domain.usecase.FetchComicsRealTime
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesScreenViewModel(
    private val deleteComicUseCase: DeleteComicUseCase,
    private val fetchComicsRealTime: FetchComicsRealTime
) :
    BaseViewModel<FavoritesScreenContract.Event, FavoritesScreenContract.State, FavoritesScreenContract.Effect>() {
    override fun setInitialState() = FavoritesScreenContract.State()

    init {
        viewModelScope.launch {
            delay(1000)
            fetchComicsRealTime().collectLatest {
                setState {
                    copy(
                        favoriteComics = it,
                        isLoading = false
                    )
                }
            }
        }
    }

    override fun handleEvents(event: FavoritesScreenContract.Event) {
        viewModelScope.launch {
            when (event) {
                is FavoritesScreenContract.Event.OnComicClicked -> {
                    setEffect {
                        FavoritesScreenContract.Effect.NavigateToComicScreen(
                            charactersUrl = event.charactersUrl,
                            comicId = event.comicId
                        )
                    }
                }

                is FavoritesScreenContract.Event.OnFavoriteIconClicked -> {
                    onFavoriteClicked(
                        charUrlsLastComicClickedOnFavorite = event.charactersUrl,
                        lastComicClickedOnFavorite = event.comic
                    )
                }

                is FavoritesScreenContract.Event.OnRemoveFavoriteComic -> {
                    deleteComic()
                }
            }
        }
    }

    private suspend fun onFavoriteClicked(
        charUrlsLastComicClickedOnFavorite: List<String>,
        lastComicClickedOnFavorite: Comic
    ) {
        withContext(Dispatchers.Main) {
            setState {
                copy(
                    charUrlsLastComicClickedOnFavorite = charUrlsLastComicClickedOnFavorite,
                    lastComicClickedOnFavorite = lastComicClickedOnFavorite
                )
            }
            setEffect {
                FavoritesScreenContract.Effect.OpenFavoriteDialog
            }
        }
    }

    private suspend fun deleteComic() {
        deleteComicUseCase(currentState.lastComicClickedOnFavorite)
        setState {
            copy(
                favoriteComics = currentState.favoriteComics.filter { comic ->
                    comic.id != currentState.lastComicClickedOnFavorite.id
                }
            )
        }
    }
}