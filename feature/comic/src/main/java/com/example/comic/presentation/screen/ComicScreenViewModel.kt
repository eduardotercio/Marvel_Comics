package com.example.comic.presentation.screen

import androidx.lifecycle.viewModelScope
import com.example.common.data.model.RequestState
import com.example.common.domain.usecase.GetCharacterUseCaseImpl
import com.example.common.presentation.base.BaseViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ComicScreenViewModel(
    private val getCharactersFromComicUseCase: GetCharacterUseCaseImpl
) :
    BaseViewModel<ComicScreenContract.Event, ComicScreenContract.State, ComicScreenContract.Effect>() {
    override fun setInitialState() = ComicScreenContract.State()

    override fun handleEvents(event: ComicScreenContract.Event) {
        viewModelScope.launch {
            when (event) {
                is ComicScreenContract.Event.FetchCharactersFromComic -> {
                    fetchCharacters(event.charactersUrl)
                }
            }
        }
    }

    private suspend fun fetchCharacters(charactersUrl: List<String>) {
        val result = getCharactersFromComicUseCase(charactersUrl)

        withContext(Dispatchers.Main) {
            when (result) {
                is RequestState.Success -> {
                    setState {
                        copy(
                            characters = result.data
                        )
                    }
                }

                is RequestState.Error -> {
                    setEffect {
                        ComicScreenContract.Effect.SnackbarErrorFindingComics
                    }
                }
            }
        }
    }
}