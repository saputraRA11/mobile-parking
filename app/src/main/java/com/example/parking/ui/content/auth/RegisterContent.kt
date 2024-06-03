package com.example.parking.ui.content.auth

import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.SpringSpec
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.requiredSizeIn
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.DropdownMenu
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.MenuDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.MutableState
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.CustomInput
import com.example.parking.ui.component.Greeting
import com.example.parking.ui.screen.auth.dto.FormRegister
import com.example.parking.ui.screen.auth.pages.RegisterScreen
import com.example.parking.ui.theme.BluePark

@Composable
fun RegisterContent(
    modifier: Modifier = Modifier,
    effect:() -> Unit = {},
    registerClick:() -> Unit = {},
    loginClick:() -> Unit = {},
    scrollState: ScrollState = rememberScrollState(),
    formUserUi: MutableState<FormRegister> = mutableStateOf(FormRegister()),
    roles:List<String> = LocalContext.current.resources.getStringArray(R.array.roles).toList(),

    ){
    var expanded by remember { mutableStateOf(false) }
    val icon = if(expanded) Icons.Filled.KeyboardArrowUp else Icons.Filled.KeyboardArrowDown
    LaunchedEffect(key1 = expanded) {
        if(expanded) {
            scrollState.animateScrollTo(scrollState.maxValue, SpringSpec(0.5f, Spring.StiffnessLow))
        } else {
            scrollState.animateScrollTo(0, SpringSpec(0.5f, Spring.StiffnessLow))
        }
    }

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
                    onClick = registerClick,
                    text = "Daftar",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = loginClick,
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
                .verticalScroll(scrollState)
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
                                        formUserUi.value = formUserUi.value.copy(phone_number = mutableStateOf(
                                            TextFieldValue("")
                                        ))
                                    },
                                borderSize = 3.dp,
                            )

                        },
                        saveState = formUserUi.value.phone_number,
                        keyboardType = KeyboardType.Number,
                        singleLine = true,
                        isNumber = true,
                        maxLimit = 13,
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
                                    formUserUi.value = formUserUi.value.copy(name = mutableStateOf(
                                        TextFieldValue("")
                                    )
                                    )
                                },
                            borderSize = 3.dp,
                        )

                    },
                    saveState = formUserUi.value.name,
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
                                    formUserUi.value = formUserUi.value.copy(nik = mutableStateOf(
                                        TextFieldValue("")
                                    )
                                    )
                                },
                            borderSize = 3.dp,
                        )

                    },
                    saveState = formUserUi.value.nik,
                    keyboardType = KeyboardType.Number,
                    singleLine = true,
                    isNumber = true,
                    maxLimit = 16,
                    modifier = Modifier
                        .fillMaxWidth()
                )
            }

            Column(
                modifier = Modifier
                    .padding(15.dp)
                    .padding(bottom = (if (expanded) 200 else 0).dp)
                    .fillMaxWidth(),
            ) {
                Text(
                    text = "Peran",
                    style = MaterialTheme.typography.h5,
                    fontWeight = FontWeight.W800
                )

                CustomInput(
                    trailing = {
                        CustomIcon(
                            color = Color.Black,
                            IconVector = icon,
                            isOutlined = true,
                            modifier = Modifier
                                .clickable {
                                },
                            borderSize = 3.dp,
                            effect = {
                                expanded = true
                            }
                        )

                    },
                    saveState = formUserUi.value.role,
                    singleLine = true,
                    maxLimit = 12,
                    modifier = modifier
                        .clickable {
                            expanded = true
                        }
                        .fillMaxWidth(),
                    fontSize = 16.sp,
                    isNumber = true,
                    isIconDisabled = false,
                    enabled = true,
                    readonly = true
                )

                DropdownMenu(
                    expanded = expanded,
                    onDismissRequest = {expanded = !expanded},
                    modifier = Modifier
                        .requiredSizeIn(maxHeight = 500.dp)
                        .fillMaxWidth(),
                )
                {
                    roles.forEach { label ->
                        DropdownMenuItem(
                            text = {
                                Text(
                                    text = label,
                                    fontWeight = FontWeight.W500
                                )
                            },
                            onClick = {
                                formUserUi.value = formUserUi.value.copy(role = mutableStateOf(
                                    TextFieldValue(label)
                                )
                                )
                                expanded = false
                            },
                            modifier = Modifier,
                            colors = MenuDefaults.itemColors(Color.Black)
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