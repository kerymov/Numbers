package com.example.numbers.presentation.ui.utils

import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.calculateEndPadding
import androidx.compose.foundation.layout.calculateStartPadding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.unit.LayoutDirection
import androidx.compose.ui.unit.dp

@Composable
fun PaddingValues.withoutBottomPadding(
    layoutDirection: LayoutDirection = LayoutDirection.Ltr
) = PaddingValues(
    top = this.calculateTopPadding(),
    bottom = 0.dp,
    start = this.calculateStartPadding(layoutDirection = layoutDirection),
    end = this.calculateEndPadding(layoutDirection = layoutDirection)
)

@Composable
fun Modifier.tapGesturesDetector(onTap: () -> Unit): Modifier {
    return this.pointerInput(Unit) {
        detectTapGestures(onTap = { onTap() })
    }
}