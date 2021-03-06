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
package com.example.androiddevchallenge.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.androiddevchallenge.components.ScreenState
import com.example.androiddevchallenge.components.ScreenTexts
import com.example.androiddevchallenge.timer
import kotlinx.coroutines.Job
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

val firstTexts = ScreenTexts(
    "SET TIME (In Seconds)", "START"
)

val secondTexts = ScreenTexts(
    "JUST RELAX", "STOP"
)

class CountDownViewModel : ViewModel() {

    private val screenStateLiveData: MutableLiveData<ScreenState> = MutableLiveData(ScreenState.SET_TIME)
    private val screenTextsLiveData: MutableLiveData<ScreenTexts> = MutableLiveData(firstTexts)
    private val targetCountDownTimeLiveData: MutableLiveData<Int> = MutableLiveData(0)
    private val currentCountDownTimeLiveData: MutableLiveData<Int> = MutableLiveData(0)
    private val timeInputBoxTextLiveData: MutableLiveData<String> = MutableLiveData("")

    val screenState: LiveData<ScreenState> get() = screenStateLiveData

    val screenTexts: LiveData<ScreenTexts> get() = screenTextsLiveData

    val timeInputBoxText: LiveData<String> get() = timeInputBoxTextLiveData

    val targetCountDownTime: LiveData<Int> get() = targetCountDownTimeLiveData

    val currentCountDownTime: LiveData<Int> get() = currentCountDownTimeLiveData

    fun changeScreenState() {
        when (screenState.value) {
            ScreenState.SET_TIME -> {
                screenStateLiveData.value = ScreenState.SHOW_TIMER
                screenTextsLiveData.value = secondTexts
                startTimer()
            }
            ScreenState.SHOW_TIMER -> {
                screenStateLiveData.value = ScreenState.SET_TIME
                screenTextsLiveData.value = firstTexts
                stopTimer()
            }
            else -> {}
        }
    }

    fun setInputBoxText(inputBoxText: String) {
        timeInputBoxTextLiveData.value = inputBoxText
    }

    fun setTargetTime(timeInSeconds: Int) {
        targetCountDownTimeLiveData.value = timeInSeconds
    }

    private var counterJob: Job? = null
    fun startTimer() {
        counterJob?.cancel()
        targetCountDownTime.value?.let {
            counterJob = timer(it)
                .onEach { currentTime ->
                    currentCountDownTimeLiveData.value = currentTime
                }
                .launchIn(viewModelScope)
        }
    }

    fun stopTimer() {
        counterJob?.cancel()
    }
}
