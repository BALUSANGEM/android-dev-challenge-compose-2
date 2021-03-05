package com.example.androiddevchallenge.components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.TileMode
import com.example.androiddevchallenge.ui.theme.counterBlueDark
import com.example.androiddevchallenge.ui.theme.counterBlueLight

@Composable
fun BlueAppBackground(
    modifier: Modifier = Modifier,
    verticalArrangement: Arrangement.Vertical = Arrangement.Top,
    horizontalAlignment: Alignment.Horizontal = Alignment.Start,
    content: @Composable() () -> Unit,
    ) {
    Column(modifier = modifier
        .background(
            brush = Brush.radialGradient(
                listOf(counterBlueDark, counterBlueLight),
                tileMode = TileMode.Clamp)
        ),
        verticalArrangement,
        horizontalAlignment
    ) {
        content()
    }
}

