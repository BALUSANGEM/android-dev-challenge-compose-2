package com.example.androiddevchallenge.components

import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.counterTextDarkBlue
import com.example.androiddevchallenge.ui.theme.counterTextLightBlue

@Composable
fun CountDownButton(label: String, onClick: () -> Unit){
    BlueAppBackground(
        modifier = Modifier
            .border(1.dp, counterTextDarkBlue, CircleShape)
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp)
            .clickable { onClick() },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center,
    ){
        Text(text = label, color= counterTextLightBlue, textAlign = TextAlign.Center, modifier = Modifier.animateContentSize(),)
    }

}