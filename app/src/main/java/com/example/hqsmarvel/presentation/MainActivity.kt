package com.example.hqsmarvel.presentation

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat
import com.example.designsystem.theme.designSystemThemePalette
import com.example.hqsmarvel.presentation.navigation.App

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            App()
            NavigationBarConfigs(this)
        }
    }
}

@Composable
private fun NavigationBarConfigs(activity: ComponentActivity) {
    val view = LocalView.current
    if (!view.isInEditMode) {
        SideEffect {
            val window = activity.window
            window.statusBarColor = designSystemThemePalette.primaryColor.toArgb()
            window.navigationBarColor = designSystemThemePalette.surfaceColor.toArgb()
            val wic = WindowCompat.getInsetsController(window, view)
            wic.isAppearanceLightStatusBars = false
            wic.isAppearanceLightNavigationBars = true
        }
    }
}