/*
 * Copyright 2021 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.example.androiddevchallenge.components

import androidx.compose.animation.Crossfade
import androidx.compose.animation.core.LinearEasing
import androidx.compose.animation.core.RepeatMode
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Path
import androidx.compose.ui.graphics.drawscope.Stroke
import androidx.compose.ui.graphics.drawscope.withTransform
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.relaxBlueDarker
import com.example.androiddevchallenge.ui.theme.whiteTextStyle
import kotlin.math.PI
import kotlin.math.cos
import kotlin.math.sin

private fun createPath(): Path {
    val sides = 10
    val radius = 250f
    val path = Path()
    val angle = 2.0 * PI / sides
    val startAngle = PI / 2.0 + Math.toRadians(360.0 / (2 * sides))
    val cx = radius / 2
    val cy = radius / 2
    path.moveTo(
        cx + (radius * cos(startAngle)).toFloat(),
        cy + (radius * sin(startAngle)).toFloat()
    )
    for (i in 1 until sides) {
        path.lineTo(
            cx + (radius * cos(startAngle - angle * i)).toFloat(),
            cy + (radius * sin(startAngle - angle * i)).toFloat()
        )
    }
    path.close()
    return path
}

@Composable
fun CounterComponent(targetTime: Int, currentTime: Int) {
    val infiniteTransition = rememberInfiniteTransition()
    val pathWidth by infiniteTransition.animateFloat(
        initialValue = 1f,
        targetValue = 10f,
        animationSpec = infiniteRepeatable(
            animation = tween(1000, easing = LinearEasing),
            repeatMode = RepeatMode.Reverse
        )
    )
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxWidth()) {
        Canvas(
            modifier = Modifier.sizeIn(250.dp),
            onDraw = {
                withTransform({
                    translate(left = -125f, top = -125f)
                }) {
                    drawPath(createPath(), style = Stroke(width = pathWidth), alpha = 1 - (pathWidth / 10), color = relaxBlueDarker)
                }
            }
        )
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(text = targetTime.toString(), style = whiteTextStyle)
            Crossfade(targetState = currentTime) {
                Text(text = currentTime.toString(), style = whiteTextStyle.copy(fontSize = 64.sp))
            }
        }
    }
}
