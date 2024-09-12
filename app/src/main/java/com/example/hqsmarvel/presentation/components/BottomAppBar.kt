package com.example.hqsmarvel.presentation.components

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationDefaults
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.res.painterResource
import androidx.navigation.NavController
import com.example.designsystem.theme.designSystemThemePalette
import com.example.hqsmarvel.presentation.model.BottomNavItem

@Composable
fun BottomAppBar(navController: NavController, items: List<BottomNavItem> = listOf()) {
    BottomNavigation(
        windowInsets = BottomNavigationDefaults.windowInsets,
        backgroundColor = designSystemThemePalette.surfaceColor,
        contentColor = designSystemThemePalette.onSurfaceColor
    ) {
        var selectedItem by remember { mutableIntStateOf(0) }

        items.forEachIndexed { index, item ->
            val label = item.route.toString()
            BottomNavigationItem(
                icon = {
                    Icon(
                        painter = painterResource(id = item.iconId),
                        contentDescription = label
                    )
                },
                label = { Text(text = label) },
                selected = selectedItem == index,
                onClick = {
                    if (selectedItem != index) {
                        navController.navigate(item.route) {
                            popUpTo(navController.graph.startDestinationId) {
                                inclusive = false
                                saveState = true
                            }
                            launchSingleTop = true
                            restoreState = true
                        }
                    }
                    selectedItem = index
                }
            )
        }
    }
}