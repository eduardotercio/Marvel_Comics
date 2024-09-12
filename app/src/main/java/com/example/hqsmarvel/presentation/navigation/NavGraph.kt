package com.example.hqsmarvel.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.toRoute
import com.example.comic.presentation.screen.HomeScreen
import com.example.common.presentation.model.Route
import com.example.splash.presentation.SplashScreen

@Composable
fun NavGraph(navController: NavHostController, startDestination: Route = Route.Splash) {

    NavHost(navController = navController, startDestination = startDestination) {
        composable<Route.Splash> {
            SplashScreen(navController)
        }
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