package com.example.androiddevchallenge.components

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.MyApp
import com.example.androiddevchallenge.ui.theme.MyTheme
import com.example.androiddevchallenge.ui.theme.appTextFont
import com.example.androiddevchallenge.ui.theme.counterTextDarkBlue
import com.example.androiddevchallenge.viewmodel.CountDownViewModel
import androidx.compose.runtime.livedata.observeAsState
import com.example.androiddevchallenge.viewmodel.firstTexts

enum class ScreenState {
    SET_TIME,
    SHOW_TIMER
}

data class ScreenTexts(
    val header: String = "",
    val buttonText: String = "",
    val inputPlaceHolder: String =""
)

@ExperimentalAnimationApi
@Composable
fun CountDownTimerScreen(countDownViewModel: CountDownViewModel){
    val screenState: ScreenState by countDownViewModel.screenState.observeAsState(ScreenState.SET_TIME)
    val screenTexts: ScreenTexts by countDownViewModel.screenTexts.observeAsState(firstTexts)
    Log.d("SCREENSTATE", screenTexts.toString())
    Log.d("SCREENSTATE", ""+screenState.toString())
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = screenTexts.header,
            modifier = Modifier.animateContentSize(),
            fontFamily = appTextFont, fontSize = 48.sp,
            color = counterTextDarkBlue
        )
        AnimatedVisibility(
            visible = screenState === ScreenState.SET_TIME) {
            CounterTimeInputBox()
        }
        CountDownButton(screenTexts.buttonText) {
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
