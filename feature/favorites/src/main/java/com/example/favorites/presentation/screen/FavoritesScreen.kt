package com.example.favorites.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.navigation.NavController
import com.example.common.presentation.components.ComicCard
import com.example.common.presentation.components.CustomDialog
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.model.Route
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.theme.designSystemThemePalette
import org.koin.androidx.compose.koinViewModel

@Composable
fun FavoritesScreen(navController: NavController) {

    val viewModel = koinViewModel<FavoritesScreenViewModel>()
    val state = viewModel.state.collectAsState().value
    val comics = state.favoriteComics

    var showDialog by remember {
        mutableStateOf(false)
    }

    LaunchedEffect(Unit) {
        viewModel.effect.collect { effect ->
            when (effect) {
                is FavoritesScreenContract.Effect.NavigateToComicScreen -> {
                    navController.navigate(Route.Comic(effect.charactersUrl, effect.comicId))
                }

                is FavoritesScreenContract.Effect.OpenFavoriteDialog -> {
                    showDialog = true
                }
            }
        }
    }

    if (showDialog) {
        CustomDialog(
            onCancelClicked = { showDialog = false },
            onContinueClicked = {
                viewModel.setEvent(FavoritesScreenContract.Event.OnRemoveFavoriteComic)
                showDialog = false
            },
            isFavorite = state.lastComicClickedOnFavorite.isFavorite
        )
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(label = stringResource(id = commonString.favorites_comics))
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
                LazyVerticalGrid(
                    modifier = Modifier
                        .fillMaxSize()
                        .padding(paddingValues),
                    horizontalArrangement = Arrangement.Center,
                    columns = GridCells.Fixed(2)
                ) {

                    items(
                        count = comics.size,
                        key = {
                            comics[it].id
                        }
                    ) { index ->
                        val comic = comics[index]
                        Column(
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.Center
                        ) {
                            Spacer(modifier = Modifier.height(Dimens.default))
                            ComicCard(
                                comic = comic,
                                onComicClicked = {
                                    viewModel.setEvent(
                                        FavoritesScreenContract.Event.OnComicClicked(
                                            comic.charactersUrl,
                                            comic.id
                                        )
                                    )
                                },
                                onFavoriteClicked = {
                                    viewModel.setEvent(
                                        FavoritesScreenContract.Event.OnFavoriteIconClicked(
                                            comic.charactersUrl,
                                            comic
                                        )
                                    )
                                }
                            )
                            Spacer(modifier = Modifier.height(Dimens.default))
                        }
                    }
                }
            }
        }
    }
}