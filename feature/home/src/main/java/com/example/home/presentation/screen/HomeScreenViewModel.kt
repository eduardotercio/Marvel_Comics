package com.example.home.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.common.data.model.RequestState
import com.example.common.domain.model.Comic
import com.example.common.domain.usecase.GetAllCharactersFromComicUseCase
import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.domain.usecase.SaveComicUseCase
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(
    private val getComicsUseCase: GetComicsUseCase,
    private val saveComicUseCase: SaveComicUseCase,
    private val getAllCharactersFromComic: GetAllCharactersFromComicUseCase
) :
    BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {
    override fun setInitialState() = HomeScreenContract.State()

    init {
        viewModelScope.launch {
            fetchComics()
        }
    }

    override fun handleEvents(event: HomeScreenContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HomeScreenContract.Event.FetchComics -> {
                    fetchComics()
                }

                is HomeScreenContract.Event.OnComicClicked -> {
                    setEffect {
                        HomeScreenContract.Effect.NavigateToComicScreen(
                            charactersUrl = event.charactersUrl,
                            comicId = event.comicId
                        )
                    }
                }

                is HomeScreenContract.Event.OnFavoriteIconClicked -> {
                    onFavoriteClicked(
                        charUrlsLastComicClickedOnFavorite = event.charactersUrl,
                        lastComicClickedOnFavorite = event.comic
                    )
                }

                is HomeScreenContract.Event.OnConfirmFavoriteComic -> {
                    saveComic()
                }
            }
        }
    }

    private suspend fun fetchComics() {
        val result = getComicsUseCase()
        withContext(Dispatchers.Main) {
            when (result) {
                is RequestState.Success -> {
                    setState {
                        copy(
                            comics = result.data
                        )
                    }
                }

                is RequestState.Error -> {
                    setEffect {
                        HomeScreenContract.Effect.SnackbarErrorFindingComics
                    }
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
                HomeScreenContract.Effect.OpenFavoriteDialog
            }
        }
    }

    private suspend fun saveComic() {
        val response = getAllCharactersFromComic(currentState.charUrlsLastComicClickedOnFavorite)
        when (response) {
            is RequestState.Success -> {
                saveComicUseCase.invoke(
                    comic = currentState.lastComicClickedOnFavorite,
                    characters = response.data
                )
            }

            is RequestState.Error -> {
                setEffect {
                    HomeScreenContract.Effect.SnackbarErrorFindingComics
                }
            }
        }
    }
}