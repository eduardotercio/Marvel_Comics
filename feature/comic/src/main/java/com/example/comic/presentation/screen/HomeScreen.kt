package com.example.comic.presentation.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.comic.presentation.components.CustomTopAppBar
import com.example.designsystem.theme.designSystemThemePalette
import org.koin.androidx.compose.koinViewModel

@Composable
fun HomeScreen(navController: NavController) {
    val viewModel = koinViewModel<HomeScreenViewModel>()
    val state = viewModel.state.collectAsStateWithLifecycle()

    Scaffold(
        topBar = {
            CustomTopAppBar()
        }
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(it)
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
            ) {
                Text(text = "series", color = designSystemThemePalette.onBackgroundColor)
                LazyRow(
                    modifier = Modifier
                        .fillMaxWidth()
                ) {
                    item { ComicCard() }
                    item { ComicCard() }
                    item { ComicCard() }
                }
            }
        }
    }
}

@Composable
fun ComicCard() {
    Column {
        AsyncImage(
            model = com.example.common.R.drawable.image_example,
            contentDescription = "Comic Image",
            contentScale = ContentScale.Fit,
        )
        Text(text = "Comic name", color = designSystemThemePalette.onBackgroundColor)
    }
}