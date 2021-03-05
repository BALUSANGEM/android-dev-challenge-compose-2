package com.example.androiddevchallenge.components

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.layout.sizeIn
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults.outlinedTextFieldColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.appTextFont
import com.example.androiddevchallenge.ui.theme.counterTextDarkBlue
import com.example.androiddevchallenge.ui.theme.counterTextLightBlue

@Composable
fun CountDownTimerScreen(){
    SetTimeComponent()
}


@Composable
fun SetTimeComponent() {
    Column(modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceAround
    ) {
        Text(
            text = "SET TIME (In Seconds)",
            fontFamily = appTextFont,
            fontSize = 48.sp,
            color = counterTextDarkBlue
        )
        CounterTimeInputBox()
        CountDownButton("START")
    }
}

@Composable
fun CounterTimeInputBox(){
    var text by remember{ mutableStateOf("") }
    val placeHolderText = "Enter"
    val textStyle = TextStyle(
        color = Color.White,
        fontFamily = appTextFont,
        fontSize = 48.sp,
        textAlign = TextAlign.Center,
    )
    Column(modifier = Modifier
        .fillMaxWidth(fraction = 0.6f)) {
        OutlinedTextField(
            value = text,
            onValueChange = { text = it },
            textStyle = textStyle,
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number),
            modifier = Modifier.fillMaxWidth().heightIn(64.dp),
            colors= outlinedTextFieldColors(textColor = Color.White),
            placeholder = {
                Text(text = placeHolderText, style = textStyle)},
            maxLines = 1
        )
    }
}

@Composable
fun CountDownButton(label: String){
    BlueAppBackground(
        modifier = Modifier
            .border(1.dp, counterTextDarkBlue, CircleShape)
            .sizeIn(minWidth = 64.dp, minHeight = 64.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ){
        Text(text = label, color= counterTextLightBlue, textAlign = TextAlign.Center)
    }

}
