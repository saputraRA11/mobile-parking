package com.example.parking.ui.screen.Form


import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.R

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGuardFormScreen(
    navController: NavHostController = rememberNavController(),
    phone: String = ""
) {
    val guardPhone = remember { mutableStateOf(TextFieldValue()) }
    val guardName = remember { mutableStateOf(TextFieldValue()) }
    val nik = remember { mutableStateOf(TextFieldValue()) }
    val expanded = remember { mutableStateOf(false) }
    val selectedArea = remember { mutableStateOf("") }
    val areaList = listOf("Area 1", "Area 2", "Area 3")

    val isFormFilled by remember {
        derivedStateOf {
            guardPhone.value.text.isNotEmpty() &&
                    guardName.value.text.isNotEmpty() &&
                    nik.value.text.isNotEmpty() &&
                    selectedArea.value.isNotEmpty()
        }
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
    ) {
        Column(
            modifier = Modifier
                .background(Color(0xFF1565C0))
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Row {
                IconButton(onClick = { navController.navigateUp() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(53.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (isFormFilled) {
                    IconButton(onClick = { navController.navigateUp() }) {
                        Image(
                            painter = painterResource(id = R.drawable.check_circle),
                            contentDescription = "Next",
                            modifier = Modifier.size(53.dp)
                        )
                    }
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = guardPhone.value,
                    onValueChange = { guardPhone.value = it },
                    placeholder = { Text(text = "Masukkan nomor penjaga", color = Color.White) },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
                        unfocusedTextColor = Color.White,
                        focusedContainerColor = Color.Transparent,
                        unfocusedContainerColor = Color.Transparent,
                        disabledContainerColor = Color.Transparent,
                        cursorColor = Color.White,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                    )
                )
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            TextField(
                value = guardName.value,
                onValueChange = { guardName.value = it },
                placeholder = { Text(text = "Nama Penjaga") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )

            TextField(
                value = nik.value,
                onValueChange = { nik.value = it },
                placeholder = { Text(text = "NIK") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.colors(
                    focusedContainerColor = Color.Transparent,
                    unfocusedContainerColor = Color.Transparent,
                    disabledContainerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray,
                )
            )

            ExposedDropdownMenuBox(
                expanded = expanded.value,
                onExpandedChange = { expanded.value = it },
                modifier = Modifier.fillMaxWidth()
            ) {
                TextField(
                    value = selectedArea.value,
                    onValueChange = { selectedArea.value = it },
                    readOnly = true,
                    placeholder = { Text(text = "Pilih Area") },
                    trailingIcon = {
                        ExposedDropdownMenuDefaults.TrailingIcon(expanded = expanded.value)
                    },
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.Gray,
                        unfocusedIndicatorColor = Color.Gray
                    ),
                    modifier = Modifier
                        .fillMaxWidth()
                        .menuAnchor()
                )

                ExposedDropdownMenu(
                    expanded = expanded.value,
                    onDismissRequest = { expanded.value = false }
                ) {
                    areaList.forEach { area ->
                        DropdownMenuItem(
                            text = { Text(text = area) },
                            onClick = {
                                selectedArea.value = area
                                expanded.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun AddGuardFormPreview() {
    AddGuardFormScreen()
}
