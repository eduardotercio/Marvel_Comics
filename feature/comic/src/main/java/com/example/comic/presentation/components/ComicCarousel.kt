package com.example.comic.presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.sp
import com.example.common.domain.model.Comic
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily

@Composable
fun ComicCarousel(
    serie: String,
    comics: List<Comic>
) {
    Column {
        Text(
            text = serie,
            fontSize = 18.sp.responsiveSp(),
            color = designSystemThemePalette.textPrimary,
            fontFamily = mavenProFontFamily,
            fontWeight = FontWeight.SemiBold,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis,
            modifier = Modifier.padding(bottom = Dimens.tiny, start = Dimens.default)
        )
        LazyRow(
            modifier = Modifier
                .fillMaxWidth()
        ) {
            item { Spacer(modifier = Modifier.width(Dimens.default)) }
            items(comics) { comic ->
                ComicCard(comic = comic)
                Spacer(modifier = Modifier.width(Dimens.default))
            }
        }
    }
}