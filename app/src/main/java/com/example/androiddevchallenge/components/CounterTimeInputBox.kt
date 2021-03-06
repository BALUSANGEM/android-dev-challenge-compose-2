package com.example.androiddevchallenge.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.androiddevchallenge.ui.theme.appTextFont

@Composable
fun CounterTimeInputBox(){
    var text by remember{ mutableStateOf("") }
    val placeHolderText = "Enter Time"
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
            keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
            modifier = Modifier.fillMaxWidth().heightIn(64.dp),
            colors= TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White),
            placeholder = {
                Text(text = placeHolderText, style = textStyle)
            },
            maxLines = 1
        )
    }
}