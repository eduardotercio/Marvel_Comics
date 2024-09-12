package com.example.comic.presentation.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.example.common.domain.model.Comic
import com.example.common.presentation.util.commonDrawable
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveDp
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily

@Composable
fun ComicCard(comic: Comic) {

    val favoriteUncheckedId = commonDrawable.ic_favorite_unchecked
    val favoriteCheckedId = commonDrawable.ic_favorite_checked

    var iconId by remember {
        mutableIntStateOf(
            if (comic.isFavorite) favoriteCheckedId
            else favoriteUncheckedId
        )
    }

    Box {
        Column(
            horizontalAlignment = Alignment.Start,
            modifier = Modifier
                .width(110.dp.responsiveDp())
        ) {
            AsyncImage(
                model = comic.thumbnail,
                contentDescription = "Comic image",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .height(140.dp.responsiveDp())
                    .clip(RoundedCornerShape(Dimens.default))
            )
            Text(
                text = comic.title,
                color = designSystemThemePalette.onBackgroundColor,
                textAlign = TextAlign.Start,
                fontFamily = mavenProFontFamily,
                fontSize = 14.sp.responsiveSp(),
                fontWeight = FontWeight.SemiBold,
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }
        Image(
            painter = painterResource(id = iconId),
            contentDescription = "Favorite icon",
            modifier = Modifier
                .align(Alignment.TopEnd)
                .padding(top = Dimens.smallAlt, end = Dimens.tinyAlt)
                .size(Dimens.mediumAlt)
                .clickable(interactionSource = null, indication = null) {
                    iconId = if (iconId == favoriteCheckedId) {
                        favoriteUncheckedId
                    } else {
                        favoriteCheckedId
                    }
                }
        )
    }
}