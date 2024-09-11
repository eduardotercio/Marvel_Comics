package com.example.comic.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.common.data.model.RequestState
import com.example.common.domain.usecase.GetComicsUseCase
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class HomeScreenViewModel(
    private val getComicsUseCase: GetComicsUseCase
) :
    BaseViewModel<HomeScreenContract.Event, HomeScreenContract.State, HomeScreenContract.Effect>() {
    override fun setInitialState() = HomeScreenContract.State()

    override fun handleEvents(event: HomeScreenContract.Event) {
        viewModelScope.launch {
            when (event) {
                is HomeScreenContract.Event.FetchComics -> {
                    fetchComics()
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
}