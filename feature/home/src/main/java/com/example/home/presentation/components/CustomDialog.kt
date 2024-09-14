package com.example.home.presentation.components

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
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.window.Dialog
import com.example.designsystem.dimens.Dimens

@Composable
fun CustomDialog(
    onCancelClicked: () -> Unit,
    onContinueClicked: () -> Unit
) {
    Dialog(onDismissRequest = {}) {
        Box(
            modifier = Modifier
                .background(Color.White, shape = RoundedCornerShape(Dimens.small))
                .padding(Dimens.default)
        ) {
            Column(modifier = Modifier.fillMaxWidth()) {

                Box(modifier = Modifier.fillMaxWidth()) {
                    IconButton(
                        onClick = onCancelClicked,
                        modifier = Modifier.align(Alignment.TopEnd)
                    ) {
                        Icon(imageVector = Icons.Default.Close, contentDescription = "Close")
                    }
                }

                Spacer(modifier = Modifier.height(Dimens.default))

                Text(text = "Are you sure you want to continue?")

                Spacer(modifier = Modifier.height(Dimens.medium))

                Row(
                    horizontalArrangement = Arrangement.End,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextButton(onClick = onCancelClicked) {
                        Text(text = "Cancel")
                    }
                    Spacer(modifier = Modifier.width(Dimens.small))
                    Button(onClick = onContinueClicked) {
                        Text(text = "Continue")
                    }
                }
            }
        }
    }
}