package com.example.parking.ui.component

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.MaterialTheme
import androidx.compose.material.OutlinedTextField
import androidx.compose.material.TextField
import androidx.compose.material.TextFieldDefaults
import androidx.compose.material.TextFieldDefaults.BackgroundOpacity
import androidx.compose.material.TextFieldDefaults.UnfocusedIndicatorLineOpacity
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shape
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.core.text.isDigitsOnly

@Composable
fun OtpInput(
    modifier: Modifier = Modifier,
    saveState: MutableState<String>,
    maxLimit: Int = 10,
    size: Int = 20,
    fontSize: Int = 20,
    repeatCount:Int = 5,
    isNumber: Boolean = true,
    rounded:Int = 5,
    ){

    BasicTextField(
        value = saveState.value,
        onValueChange = { if(it.length <= maxLimit && (isNumber && it.isDigitsOnly())) saveState.value= it},
        keyboardOptions = KeyboardOptions(
            keyboardType = KeyboardType.NumberPassword
        ),
        modifier = modifier
            .fillMaxSize()
    ) {
        Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = modifier
                .fillMaxWidth()
        ) {
            repeat(repeatCount) {index ->
                val number = when {
                    index >= saveState.value.length -> ""
                    else -> saveState.value[index].toString()
                }

                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.spacedBy(1.dp),
                    modifier = Modifier
                        .background(
                            MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity),
                            shape = RoundedCornerShape((rounded).dp)
                        )
                ) {
                    Box(
                        modifier = Modifier
                            .width((size).dp)
                            .height(1.dp)
                            .background(Color.Transparent)
                    )

                    Text(
                        text = number.toString(),
                        style = MaterialTheme.typography.h1,
                        fontSize = (fontSize).sp
                    )
                }
            }
        }
    }
}

@Composable
fun CustomInput(
    modifier: Modifier = Modifier,
    trailing: @Composable () -> Unit = {},
    isBackgroundTransparent:Boolean = true,
    isIndicatorTransparent:Boolean = false,
    backgroundColor:Color = MaterialTheme.colors.onSurface.copy(alpha = BackgroundOpacity),
    unfocusedIndicatorsColor:Color = MaterialTheme.colors.onSurface.copy(alpha = UnfocusedIndicatorLineOpacity),
    shape:Shape = TextFieldDefaults.TextFieldShape,
    fontSize:TextUnit = 20.sp,
    singleLine: Boolean = false,
    maxLimit:Int = 100,
    textAlign:TextAlign = TextAlign.Start,
    keyboardType:KeyboardType = KeyboardType.Text,
    saveState:  MutableState<TextFieldValue>,
    isNumber:Boolean = false,
    isIconDisabled:Boolean = true,
    readonly:Boolean = false,
    enabled:Boolean = true,
    fontWeight: FontWeight = FontWeight.Normal
    ) {

    TextField(
        value = saveState.value,
        onValueChange = {
            if(it.text.length <= maxLimit) {
                if(isNumber && it.text.isDigitsOnly()) saveState.value = it
                else if(!isNumber) saveState.value = it
            }
        },
        readOnly = readonly ,
        textStyle = TextStyle(
            fontSize = fontSize,
            fontWeight = fontWeight,
            textAlign = textAlign
        ),
        colors = TextFieldDefaults.textFieldColors(
            backgroundColor = if(isBackgroundTransparent) Color.Transparent else backgroundColor,
            unfocusedIndicatorColor = if(isIndicatorTransparent) Color.Transparent else unfocusedIndicatorsColor
        ),
        trailingIcon = {
           if(isIconDisabled) {
               if(saveState.value.text.isNotEmpty()) {
                   trailing()
               }
           } else {
               trailing()
           }
        },
        keyboardOptions = KeyboardOptions(
            keyboardType = keyboardType
        ),
        shape = shape,
        singleLine = singleLine,
        modifier = modifier,
        enabled = enabled
    )
}

@Preview(showBackground = true)
@Composable
fun CustomInputPreview(){
    Column (
        modifier = Modifier
            .fillMaxSize()
            .padding(20.dp),
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        val saveState = remember {
            mutableStateOf(TextFieldValue(""))
        }

        CustomInput(
            trailing = {
                CustomIcon(
                    color = Color.Black,
                    IconVector = Icons.Default.Clear,
                    isOutlined = true,
                    modifier = Modifier
                        .clickable {
                                saveState.value = TextFieldValue("")
                        },
                    borderSize = 3.dp,
                )

            },
            saveState = saveState,
        )

        val otpState = remember {
            mutableStateOf("")
        }
        OtpInput(
            saveState = otpState,
            maxLimit = 5,
            size = 55,
            fontSize = 40,
            rounded = 15
        )

    }
}