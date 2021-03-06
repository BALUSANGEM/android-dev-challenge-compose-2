package com.example.androiddevchallenge.viewmodel

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.androiddevchallenge.components.ScreenState
import com.example.androiddevchallenge.components.ScreenTexts

val firstTexts = ScreenTexts(
    "SET TIME (In Seconds)", "START"
)

val secondTexts = ScreenTexts(
    "JUST RELAX", "STOP"
)

class CountDownViewModel : ViewModel() {

    private val screenStateLiveData : MutableLiveData<ScreenState> = MutableLiveData(ScreenState.SET_TIME)
    private val screenTextsLiveData : MutableLiveData<ScreenTexts> = MutableLiveData(firstTexts)

    val screenState: LiveData<ScreenState> get() = screenStateLiveData

    val screenTexts: LiveData<ScreenTexts> get() = screenTextsLiveData

    fun changeScreenState() {
        Log.d("SCREENSTATE", "Changge screen from : " + screenState.value.toString())
        when(screenState.value){
            ScreenState.SET_TIME -> {
                Log.d("SCREENSTATE", "Came here 1: ")
                screenStateLiveData.value = ScreenState.SHOW_TIMER
                screenTextsLiveData.value = secondTexts
            }
            ScreenState.SHOW_TIMER ->  {
                Log.d("SCREENSTATE", "Came here 2: ")
                screenStateLiveData.value = ScreenState.SET_TIME
                screenTextsLiveData.value = firstTexts
            }
            else -> {}
        }
    }

}