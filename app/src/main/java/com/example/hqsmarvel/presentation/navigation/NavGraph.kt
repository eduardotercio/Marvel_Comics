package com.example.hqsmarvel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.home.presentation.screen.HomeScreen
import com.example.common.presentation.model.Route

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

        }
        composable<Route.Comic> {
            val args = it.toRoute<Route.Comic>()
            val characterUrl = args.characterUrl
        }
    }
}