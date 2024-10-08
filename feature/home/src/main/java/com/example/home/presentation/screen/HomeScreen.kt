package com.example.home.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.presentation.components.CustomDialog
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.model.Route
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.theme.designSystemThemePalette
import com.example.home.presentation.components.ComicCarousel
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle().value
    val comics = state.comics
    val seriesList by remember(comics) {
        mutableStateOf(comics.map { it.series }.toSet().toList())
    }
    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is HomeScreenContract.Effect.NavigateToComicScreen -> {
                    navController.navigate(Route.Comic(effect.charactersUrl, effect.comicId))
                }

                is HomeScreenContract.Effect.OpenFavoriteDialog -> {
                    showDialog = true
                }

                is HomeScreenContract.Effect.SnackbarErrorFindingComics -> {

                }
            }
        }
    }

    if (showDialog) {
        CustomDialog(
            onCancelClicked = { showDialog = false },
            onContinueClicked = {
                if (state.lastComicClickedOnFavorite.isFavorite) {
                    viewModel.setEvent(HomeScreenContract.Event.OnRemoveFavoriteComic)
                } else {
                    viewModel.setEvent(HomeScreenContract.Event.OnConfirmFavoriteComic)
                }
                showDialog = false
            },
            isFavorite = state.lastComicClickedOnFavorite.isFavorite
        )
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(stringResource(id = commonString.marvel_comics))
        }
    ) { paddingValues ->
        if (state.isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                CircularProgressIndicator(
                    color = designSystemThemePalette.onBackgroundColor,
                    modifier = Modifier.align(
                        Alignment.Center
                    )
                )
            }
        } else {
            if (comics.isEmpty()) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                ) {
                    Text(
                        text = stringResource(id = commonString.no_comic_available),
                        modifier = Modifier.align(
                            Alignment.Center
                        )
                    )
                }
            } else {
                Spacer(modifier = Modifier.height(Dimens.big))
                LazyColumn(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues)
                ) {
                    items(
                        count = seriesList.size,
                        key = { seriesList[it] }
                    ) { index ->
                        val series = seriesList[index]
                        ComicCarousel(
                            serie = series,
                            comics = comics.filter { it.series == series },
                            onComicClicked = { charactersUrl, comicId ->
                                viewModel.setEvent(
                                    HomeScreenContract.Event.OnComicClicked(
                                        charactersUrl,
                                        comicId
                                    )
                                )
                            },
                            onFavoriteClicked = { charactersUrl, comic ->
                                viewModel.setEvent(
                                    HomeScreenContract.Event.OnFavoriteIconClicked(
                                        charactersUrl,
                                        comic
                                    )
                                )
                            }
                        )
                        Spacer(modifier = Modifier.height(Dimens.big))
                    }
                }
            }
        }
    }
}