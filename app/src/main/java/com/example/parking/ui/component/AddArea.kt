package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R // Pastikan ini mengarah ke package aplikasi Anda

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ParkingAreaForm() {
    val namaArea = remember { mutableStateOf(TextFieldValue()) }
    val jalanLengkap = remember { mutableStateOf(TextFieldValue()) }
    val biayaMobil = remember { mutableStateOf(TextFieldValue()) }
    val biayaMotor = remember { mutableStateOf(TextFieldValue()) }

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
            IconButton(onClick = { /* Handle back action */ }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = Color.White,
                    modifier = Modifier.size(53.dp)
                )
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Box(
                    modifier = Modifier
                        .size(80.dp)
                        .background(Color(0xFF0D47A1), shape = CircleShape),
                    contentAlignment = Alignment.Center
                ) {
                    IconButton(onClick = { /* Handle camera action */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.add_a_photo),
                            contentDescription = "Camera Picture",
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                TextField(
                    value = namaArea.value,
                    onValueChange = { namaArea.value = it },
                    placeholder = { Text(text = "Nama Area", color = Color.White) },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.textFieldColors(
                        containerColor = Color.Transparent,
                        focusedIndicatorColor = Color.White,
                        unfocusedIndicatorColor = Color.White,
                        cursorColor = Color.White,
                        focusedTextColor = Color.White
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
                value = jalanLengkap.value,
                onValueChange = { jalanLengkap.value = it },
                placeholder = { Text(text = "Jalan Lengkap") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            TextField(
                value = biayaMobil.value,
                onValueChange = { biayaMobil.value = it },
                placeholder = { Text(text = "Biaya Parkir Mobil Tiap Jam") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray
                )
            )

            TextField(
                value = biayaMotor.value,
                onValueChange = { biayaMotor.value = it },
                placeholder = { Text(text = "Biaya Parkir Motor Tiap Jam") },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 8.dp),
                colors = TextFieldDefaults.textFieldColors(
                    containerColor = Color.Transparent,
                    focusedIndicatorColor = Color.Gray,
                    unfocusedIndicatorColor = Color.Gray
                )
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingAreaFormPreview() {
    ParkingAreaForm()
}
