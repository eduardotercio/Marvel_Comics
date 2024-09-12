package com.example.splash.presentation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import com.example.common.presentation.model.Route

@Composable
fun SplashScreen(navController: NavController) {
    navController.popBackStack()
    navController.navigate(Route.Home)
}