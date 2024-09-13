package com.example.comic.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveDp
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicScreen(
    navController: NavController,
    charactersUrl: List<String>
) {

    val viewModel = koinViewModel<ComicScreenViewModel>()
    val state = viewModel.state.collectAsState().value
    val characters = state.characters

    LaunchedEffect(Unit) {
        viewModel.setEvent(ComicScreenContract.Event.FetchCharactersFromComic(charactersUrl))
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(stringResource(id = commonString.marvel_characters))
        }
    ) { paddingValues ->
        if (characters.isEmpty()) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "no character available", modifier = Modifier.align(Alignment.Center))
            }
        } else {
            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                items(
                    count = characters.size,
                    key = {
                        characters[it].id
                    }
                ) { index ->
                    val character = characters[index]
                    Column {
                        AsyncImage(
                            model = character.imageUrl,
                            contentDescription = stringResource(id = commonString.image_description_comic),
                            contentScale = ContentScale.Fit,
                            modifier = Modifier
                                .height(160.dp.responsiveDp())
                                .width(130.dp.responsiveDp())
                                .clip(RoundedCornerShape(Dimens.default))
                        )
                        Text(text = character.name)
                    }
                }
            }
        }
    }
}