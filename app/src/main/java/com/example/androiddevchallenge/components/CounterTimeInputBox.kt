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

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.Text
import androidx.compose.material.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import com.example.androiddevchallenge.ui.theme.whiteTextStyle

@Composable
fun CounterTimeInputBox(
    inputText: String,
    placeHolderText: String = "Enter Time",
    onTextChange: (String) -> Unit
) {

    OutlinedTextField(
        value = inputText,
        onValueChange = { onTextChange(it) },
        textStyle = whiteTextStyle,
        keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Number, imeAction = ImeAction.Done),
        modifier = Modifier.fillMaxWidth(fraction = 0.6f).heightIn(64.dp),
        colors = TextFieldDefaults.outlinedTextFieldColors(textColor = Color.White),
        placeholder = {
            Text(text = placeHolderText, style = whiteTextStyle)
        },
        maxLines = 1
    )
}
