package com.example.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily

@Composable
fun CustomTopAppBar(
    label: String,
    showNavigationIcon: Boolean = false,
    navController: NavController? = null
) {
    Box(
        modifier = Modifier
            .fillMaxWidth()
            .background(designSystemThemePalette.primaryColor),
    ) {
        if (showNavigationIcon) {
            Icon(
                imageVector = Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "",
                modifier = Modifier
                    .align(Alignment.CenterStart)
                    .padding(start = Dimens.default)
                    .clickable {
                        navController?.popBackStack()
                    }
            )
        }
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier.align(Alignment.Center)
        ) {
            Text(
                text = label,
                textAlign = TextAlign.Center,
                color = designSystemThemePalette.textPrimary,
                fontFamily = mavenProFontFamily,
                fontWeight = FontWeight.Bold,
                fontSize = 20.sp.responsiveSp(),
                modifier = Modifier.padding(top = Dimens.small, bottom = Dimens.defaultAlt)
            )
            Divider(color = designSystemThemePalette.onPrimaryColor)
        }
    }
}