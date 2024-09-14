package com.example.favorites.presentation.screen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import com.example.common.presentation.components.CustomTopAppBar
import com.example.common.presentation.util.commonString

@Composable
fun FavoritesScreen() {

    Scaffold(
        topBar = {
            CustomTopAppBar(label = stringResource(id = commonString.favorites_comics))
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues)) {

        }
    }
}