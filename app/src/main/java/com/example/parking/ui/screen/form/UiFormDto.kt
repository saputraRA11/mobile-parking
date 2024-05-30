package com.example.parking.ui.screen.form

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

data class AddAreaForm(
    val namaArea : MutableState<TextFieldValue> =  mutableStateOf(TextFieldValue()),
    val jalanLengkap : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val biayaMobil : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val biayaMotor : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val userId:MutableState<String> = mutableStateOf("")
)
data class AddGuardForm(
    val guardPhone : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val guardName : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val nik : MutableState<TextFieldValue> = mutableStateOf(TextFieldValue()),
    val selectedArea : MutableState<String> = mutableStateOf(""),
)

data class Area(
    val id: String = "",
    val name: String = "",
    val guardCount: Int = 0
)