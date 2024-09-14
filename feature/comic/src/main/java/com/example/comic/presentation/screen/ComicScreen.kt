package com.example.comic.presentation.screen

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.CircularProgressIndicator
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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveDp
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily
import org.koin.androidx.compose.koinViewModel

@Composable
fun ComicScreen(
    navController: NavController,
    charactersUrl: List<String>,
    comicId: Int
) {
    val viewModel = koinViewModel<ComicScreenViewModel>()
    val state = viewModel.state.collectAsState().value
    val characters = state.characters
    val comicHasCharacters = charactersUrl.isNotEmpty()

    LaunchedEffect(Unit) {
        if (comicHasCharacters) {
            viewModel.setEvent(ComicScreenContract.Event.LoadNextItems(charactersUrl, comicId))
        }
    }

    Scaffold(
        topBar = {
            CustomTopAppBar(
                label = stringResource(id = commonString.marvel_characters),
                showNavigationIcon = true,
                navController = navController
            )
        }
    ) { paddingValues ->
        if (!comicHasCharacters) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
            ) {
                Text(text = "no character available", modifier = Modifier.align(Alignment.Center))
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
                    count = characters.size,
                    key = {
                        characters[it].id
                    }
                ) { index ->
                    val character = characters[index]
                    if (index >= characters.size - 1 && !state.endReached && !state.isLoading) {
                        viewModel.setEvent(
                            ComicScreenContract.Event.LoadNextItems(
                                charactersUrl,
                                comicId
                            )
                        )
                    }
                    Column(
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.Center
                    ) {
                        Spacer(modifier = Modifier.height(Dimens.mediumAlt))
                        AsyncImage(
                            model = character.imageUrl,
                            contentDescription = stringResource(id = commonString.image_description_comic),
                            contentScale = ContentScale.Crop,
                            modifier = Modifier
                                .height(160.dp.responsiveDp())
                                .width(160.dp.responsiveDp())
                                .clip(RoundedCornerShape(100))
                        )
                        Spacer(modifier = Modifier.height(Dimens.small))
                        Text(
                            text = character.name,
                            color = designSystemThemePalette.onBackgroundColor,
                            textAlign = TextAlign.Center,
                            fontFamily = mavenProFontFamily,
                            fontSize = 16.sp.responsiveSp(),
                            fontWeight = FontWeight.SemiBold,
                            maxLines = 3,
                            overflow = TextOverflow.Ellipsis,
                            modifier = Modifier.width(130.dp.responsiveDp())
                        )
                        Spacer(modifier = Modifier.height(Dimens.medium))
                    }
                }
                if (state.isLoading && characters.size != charactersUrl.size) {
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(color = designSystemThemePalette.onBackgroundColor)
                        }
                    }
                    item {
                        Row(
                            modifier = Modifier
                                .fillMaxWidth()
                                .padding(8.dp),
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            CircularProgressIndicator(color = designSystemThemePalette.onBackgroundColor)
                        }
                    }
                }
            }
        }
    }
}