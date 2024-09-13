package com.example.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Divider
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily

@Composable
fun CustomTopAppBar(label: String) {
    Column(
        modifier = Modifier
            .fillMaxWidth()
            .background(designSystemThemePalette.primaryColor),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = label,
            textAlign = TextAlign.Center,
            color = designSystemThemePalette.textPrimary,
            fontFamily = mavenProFontFamily,
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp.responsiveSp(),
            modifier = Modifier.padding(bottom = Dimens.defaultAlt)
        )
        Divider(color = designSystemThemePalette.onPrimaryColor)
    }
}