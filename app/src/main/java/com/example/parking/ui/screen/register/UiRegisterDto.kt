package com.example.parking.ui.screen.register

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.text.input.TextFieldValue

data class FormRegister(
    val phone_number:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),
    val name:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),
    val nik:MutableState<TextFieldValue> =mutableStateOf(TextFieldValue("")),
    val role:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue(""))
)

