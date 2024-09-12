package com.example.hqsmarvel.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.example.designsystem.theme.designSystemThemePalette
import com.example.hqsmarvel.presentation.model.BottomNavItem

@Composable
fun BottomAppBar(navController: NavController, items: List<BottomNavItem> = listOf()) {
    val currentDestination by navController.currentBackStackEntryAsState()
    BottomNavigation(
        windowInsets = BottomNavigationDefaults.windowInsets,
        backgroundColor = designSystemThemePalette.surfaceColor,
        contentColor = designSystemThemePalette.onSurfaceColor
    ) {
        items.forEach { item ->
            val label = item.route.toString()
            BottomNavigationItem(
                selected = currentDestination?.destination?.route?.contains(item.route.toString()) == true,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId) {
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                },
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = label
                    )
                },
                label = { Text(text = label) }
            )
        }
    }
}