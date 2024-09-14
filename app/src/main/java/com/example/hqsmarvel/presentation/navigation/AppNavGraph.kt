package com.example.hqsmarvel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.comic.presentation.screen.ComicScreen
import com.example.common.presentation.model.Route
import com.example.favorites.presentation.screen.FavoritesScreen
import com.example.home.presentation.screen.HomeScreen

@Composable
fun NavGraph(navController: NavHostController) {
    NavHost(
        navController = navController,
        startDestination = Route.Home
    ) {
        composable<Route.Home> {
            HomeScreen(navController)
        }
        composable<Route.Favorite> {
            FavoritesScreen(navController)
        }
        composable<Route.Comic> {
            val args = it.toRoute<Route.Comic>()
            val charactersUrl = args.charactersUrl
            val comicId = args.comicId
            ComicScreen(
                navController = navController,
                charactersUrl = charactersUrl,
                comicId = comicId
            )
        }
    }
}