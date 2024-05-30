package com.example.parking.ui.content.auth

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.CustomInput
import com.example.parking.ui.component.Greeting
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.theme.BluePark

@Composable
fun LoginContent(
    phoneNumber:MutableState<TextFieldValue> = mutableStateOf(TextFieldValue("")),
    effect:() -> Unit = {},
    registerClick:() -> Unit = {},
    loginClick:() -> Unit = {}
){
    Scaffold (
        topBar = {
            Row (
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ){
                CustomIcon(
                    color = Color.Black,
                    IconVector = Icons.AutoMirrored.Filled.KeyboardArrowLeft,
                    isOutlined = false,
                    modifier = Modifier.size(50.dp),
                    effect = effect
                )

                CustomIcon(
                    color = Color.Black,
                    IconVector = ImageVector.vectorResource(id = R.drawable.help_support_foreground),
                    isOutlined = false ,
                    modifier = Modifier.size(50.dp)
                )
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 10.dp)
                    .fillMaxWidth()
            ) {
                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = loginClick,
                    text = "Masuk",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = registerClick,
                    text = "Pengguna Baru? Buat akun",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                    isOutlined = true,
                    border = BorderStroke(1.dp, BluePark)
                )
            }
        },
        modifier = Modifier
            .fillMaxSize()
    ) {
            innerPadding ->
        Column(
            modifier = Modifier
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
        ) {
            Greeting(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
                isRegister = false
            )

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Nomor Hp",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W500
                )

                Row(
                    modifier = Modifier
                        .fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(30.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "+62",
                        style = MaterialTheme.typography.h6,
                        modifier = Modifier
                            .padding(start = 40.dp)
                    )
                    CustomInput(
                        trailing = {
                            CustomIcon(
                                color = Color.Black,
                                IconVector = Icons.Default.Clear,
                                isOutlined = true,
                                modifier = Modifier
                                    .clickable {
                                        phoneNumber.value = TextFieldValue("")
                                    },
                                borderSize = 3.dp,
                            )

                        },
                        saveState = phoneNumber,
                        keyboardType = KeyboardType.Number,
                        singleLine = true,
                        isNumber = true,
                        maxLimit = 12,
                        modifier = Modifier
                            .fillMaxWidth()
                    )
                }
            }
        }
    }
}