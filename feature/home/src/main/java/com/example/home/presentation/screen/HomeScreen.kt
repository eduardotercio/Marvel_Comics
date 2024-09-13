package com.example.home.presentation.screen

import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.model.Route
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
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

    Scaffold(
        topBar = {
            CustomTopAppBar(stringResource(id = commonString.marvel_comics))
        }
    ) { paddingValues ->
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
                        navController.navigate(Route.Comic(charactersUrl, comicId))
                    }
                )
                Spacer(modifier = Modifier.height(Dimens.big))
            }
        }
    }
}