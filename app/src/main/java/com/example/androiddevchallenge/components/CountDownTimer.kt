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
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.appTextFont
import com.example.androiddevchallenge.ui.theme.counterTextDarkBlue
import com.example.androiddevchallenge.viewmodel.CountDownViewModel
import com.example.androiddevchallenge.viewmodel.firstTexts

enum class ScreenState {
    SET_TIME,
    SHOW_TIMER
}

data class ScreenTexts(
    val header: String = "",
    val buttonText: String = "",
    val inputPlaceHolder: String = ""
)

@ExperimentalAnimationApi
@Composable
fun CountDownTimerScreen(countDownViewModel: CountDownViewModel) {
    val screenState: ScreenState by countDownViewModel.screenState.observeAsState(ScreenState.SET_TIME)
    val screenTexts: ScreenTexts by countDownViewModel.screenTexts.observeAsState(firstTexts)
    val inputBoxText: String by countDownViewModel.timeInputBoxText.observeAsState("")

    val targetTime: Int by countDownViewModel.targetCountDownTime.observeAsState(0)
    val currentTime: Int by countDownViewModel.currentCountDownTime.observeAsState(0)

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                top = 48.dp,
                bottom = 48.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceBetween
    ) {
        Crossfade(targetState = screenState) {
            Text(
                text = screenTexts.header,
                fontFamily = appTextFont, fontSize = 48.sp,
                color = counterTextDarkBlue
            )
        }

        Crossfade(targetState = screenState) { screen ->
            when (screen) {
                ScreenState.SET_TIME -> {
                    CounterTimeInputBox(inputBoxText) {
                        countDownViewModel.setInputBoxText(it)
                    }
                }
                ScreenState.SHOW_TIMER -> {
                    CounterComponent(targetTime, currentTime)
                }
            }
        }

        CountDownButton(screenTexts.buttonText) {
            countDownViewModel.setTargetTime(inputBoxText.toInt())
            countDownViewModel.changeScreenState()
        }
    }
}

@ExperimentalAnimationApi
@Preview("Light Theme", widthDp = 360, heightDp = 640)
@Composable
fun LightPreview() {
    MyTheme {
        MyApp()
    }
}
