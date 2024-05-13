package com.example.parking.ui.screen.register

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
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.vectorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.DpOffset
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.CustomInput
import com.example.parking.ui.component.Greeting
import com.example.parking.ui.theme.BluePark

@Composable
fun RegisterScreen(
    modifier: Modifier = Modifier
) {
    val phoneNumber = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val name = remember {
        mutableStateOf(TextFieldValue(""))
    }

    val nik = remember {
        mutableStateOf(TextFieldValue(""))
    }

    var expanded by remember { mutableStateOf(false) }
    val roles = LocalContext.current.resources.getStringArray(R.array.roles).toList()
    var selectedText = remember { mutableStateOf(TextFieldValue("")) }
    val icon = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown

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
                        IconVector = Icons.Default.KeyboardArrowLeft,
                        isOutlined = false,
                        modifier = Modifier.size(50.dp)
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
                    onClick = {},
                    text = "Daftar",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    text = "Sudah punya akun? Masuk",
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

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Nama Pengguna",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W500
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
                        saveState = name,
                        singleLine = true,
                        maxLimit = 30,
                        modifier = modifier
                            .fillMaxWidth(),
                        fontSize = 16.sp,
                    )
            }

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "NIK",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W500
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
                    saveState = nik,
                    singleLine = true,
                    maxLimit = 12,
                    modifier = modifier
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    isNumber = true
                )
            }

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Peran",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W500
                )

                CustomInput(
                    trailing = {
                        CustomIcon(
                            color = Color.Black,
                            IconVector = icon,
                            isOutlined = true,
                            modifier = Modifier
                                .clickable {
                                    expanded = !expanded
                                },
                            borderSize = 3.dp,
                        )

                    },
                    saveState = selectedText,
                    singleLine = true,
                    maxLimit = 12,
                    modifier = modifier
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    isNumber = true,
                    isIconDisabled = false
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = false},
                    modifier = Modifier.fillMaxWidth(),
                    offset = DpOffset(y= (-100).dp,x=0.dp)
                )
                {
                    roles.forEach { label ->
                        DropdownMenuItem(
                            text = { Text(text = label) },
                            onClick = {
                                selectedText.value = TextFieldValue(label)
                                expanded = false
                            },
                            modifier = Modifier
                        )
                    }
                }
            }

        }
    }
}

@Preview(showBackground = true)
@Composable
fun RegisterPreview(){
    RegisterScreen()
}