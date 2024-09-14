package com.example.common.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.window.Dialog
import com.example.common.presentation.util.commonString
import com.example.designsystem.dimens.Dimens
import com.example.designsystem.dimens.responsiveSp
import com.example.designsystem.theme.designSystemThemePalette
import com.example.designsystem.theme.mavenProFontFamily

@Composable
fun CustomDialog(
    onCancelClicked: () -> Unit,
    onContinueClicked: () -> Unit,
    isFavorite: Boolean
) {

    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .background(
                    designSystemThemePalette.surfaceColor,
                    shape = RoundedCornerShape(Dimens.small)
                )
                .padding(Dimens.default)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {
                Box(modifier = Modifier.fillMaxWidth()) {
                    Text(
                        text = if (isFavorite) {
                            stringResource(id = commonString.unfavorite)
                        } else {
                            stringResource(id = commonString.favorite)
                        },
                        color = designSystemThemePalette.textPrimary,
                        fontSize = 20.sp.responsiveSp(),
                        fontFamily = mavenProFontFamily,
                        fontWeight = FontWeight.Bold,
                        modifier = Modifier
                            .align(Alignment.TopCenter)
                            .padding(top = Dimens.defaultAlt),
                    )
                    IconButton(
                        onClick = onCancelClicked,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(
                            imageVector = Icons.Default.Close,
                            contentDescription = stringResource(id = commonString.dialog_close_icon),
                            tint = designSystemThemePalette.onSurfaceColor
                        )
                    }
                }
                Spacer(modifier = Modifier.height(Dimens.defaultAlt))
                Text(
                    text = if (isFavorite) {
                        stringResource(id = commonString.unfavorite_dialog_description)
                    } else {
                        stringResource(id = commonString.favorite_dialog_description)
                    },
                    color = designSystemThemePalette.textSecondary,
                    modifier = Modifier.padding(horizontal = Dimens.small),
                    fontFamily = mavenProFontFamily,
                    fontWeight = FontWeight.SemiBold,
                )
                Spacer(modifier = Modifier.height(Dimens.medium))
                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(
                        onClick = onCancelClicked,
                        modifier = Modifier.background(designSystemThemePalette.surfaceColor)
                    ) {
                        Text(
                            text = stringResource(id = commonString.cancel_dialog),
                            color = designSystemThemePalette.onSurfaceColor
                        )
                    }
                    Spacer(modifier = Modifier.width(Dimens.small))
                    Button(
                        onClick = onContinueClicked,
                        colors = ButtonDefaults.buttonColors(backgroundColor = designSystemThemePalette.onSurfaceColor)
                    ) {
                        Text(
                            text = stringResource(id = commonString.continue_dialog),
                            color = designSystemThemePalette.surfaceColor
                        )
                    }
                }
            }
        }
    }
}