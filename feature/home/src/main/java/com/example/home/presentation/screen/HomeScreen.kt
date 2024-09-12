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
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import com.example.home.presentation.components.ComicCarousel
import com.example.home.presentation.components.CustomTopAppBar
import com.example.designsystem.dimens.Dimens
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
            CustomTopAppBar()
        }
    ) { paddingValues ->
        Spacer(modifier = Modifier.height(Dimens.medium))
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
                    comics = comics.filter { it.series == series }
                )
                Spacer(modifier = Modifier.height(Dimens.big))
            }
        }
    }
}