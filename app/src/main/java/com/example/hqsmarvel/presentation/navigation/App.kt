package com.example.hqsmarvel.presentation.navigation

import android.annotation.SuppressLint
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController
import com.example.common.presentation.model.Route
import com.example.designsystem.theme.MarvelComicsTheme
import com.example.hqsmarvel.R
import com.example.hqsmarvel.presentation.components.BottomAppBar
import com.example.hqsmarvel.presentation.model.BottomNavItem

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun App() {
    MarvelComicsTheme {
        val navController = rememberNavController()
        Scaffold(
            bottomBar = {
                BottomAppBar(
                    navController = navController,
                    items = listOf(
                        BottomNavItem(
                            route = Route.Home,
                            iconId = R.drawable.ic_home
                        ),
                        BottomNavItem(
                            route = Route.Favorite,
                            iconId = R.drawable.ic_favorite
                        )
                    )
                )
            }
        ) {
            NavGraph(navController = navController)
        }
    }
}