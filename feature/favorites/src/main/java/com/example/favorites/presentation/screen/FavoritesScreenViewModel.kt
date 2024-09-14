package com.example.favorites.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.common.data.model.RequestState
import com.example.common.domain.model.Comic
import com.example.common.domain.usecase.DeleteComicUseCase
import com.example.common.domain.usecase.FetchComicsRealTime
import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collectLatest
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class FavoritesScreenViewModel(
    private val getComicsUseCase: GetComicsUseCase,
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

    private suspend fun fetchComics() {
        withContext(Dispatchers.Main) {
            val response = getComicsUseCase(onlyLocal = true)
            when (response) {
                is RequestState.Success -> {
                    setState {
                        copy(
                            favoriteComics = response.data
                        )
                    }
                }

                is RequestState.Error -> {

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