package com.example.parking.ui.component

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
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
import com.example.parking.R

@Composable
fun ParkingAreaForm() {
    val namaArea = remember { mutableStateOf(TextFieldValue()) }
    val jalanLengkap = remember { mutableStateOf(TextFieldValue()) }
    val biayaMobil = remember { mutableStateOf(TextFieldValue()) }
    val biayaMotor = remember { mutableStateOf(TextFieldValue()) }

    val isFormFilled by remember {
        derivedStateOf {
            namaArea.value.text.isNotEmpty() &&
                    jalanLengkap.value.text.isNotEmpty() &&
                    biayaMobil.value.text.isNotEmpty() &&
                    biayaMotor.value.text.isNotEmpty()
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
                IconButton(onClick = { /* Handle back action */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(53.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (isFormFilled) {
                    IconButton(onClick = { /* Handle next action */ }) {
                        Image(
                            painter = painterResource(id = R.drawable.arrow_circle_right),
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
                            modifier = Modifier.size(36.dp)
                        )
                    }
                }

                Spacer(modifier = Modifier.width(16.dp))

                TextField(
                    value = namaArea.value,
                    onValueChange = { namaArea.value = it },
                    placeholder = { Text(text = "Nama Area", color = Color.White) },
                    modifier = Modifier.weight(1f),
                    colors = TextFieldDefaults.colors(
                        focusedTextColor = Color.White,
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
                value = jalanLengkap.value,
                onValueChange = { jalanLengkap.value = it },
                placeholder = { Text(text = "Jalan Lengkap") },
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
                value = biayaMobil.value,
                onValueChange = { biayaMobil.value = it },
                placeholder = { Text(text = "Biaya Parkir Mobil Tiap Jam") },
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
                value = biayaMotor.value,
                onValueChange = { biayaMotor.value = it },
                placeholder = { Text(text = "Biaya Parkir Motor Tiap Jam") },
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
        }
    }
}

@Composable
fun EditParkingArea() {
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
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                IconButton(onClick = { /* Handle back action */ }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle next action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.check_circle),
                        contentDescription = "Next",
                        tint = Color.White,
                        modifier = Modifier.size(24.dp)
                    )
                }
            }

            Spacer(modifier = Modifier.height(16.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
                    .padding(10.dp),
                horizontalArrangement = Arrangement.Center
            ) {
                Box(
                    modifier = Modifier.size(100.dp),
                    contentAlignment = Alignment.BottomEnd
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.profile),
                        contentDescription = "Profile Picture",
                        modifier = Modifier
                            .size(100.dp)
                            .background(Color.Gray, shape = CircleShape)
                    )
                    IconButton(
                        onClick = { /* Handle camera action */ },
                        modifier = Modifier
                            .size(32.dp)
                            .background(Color(0xFF1565C0), shape = CircleShape)
                            .align(Alignment.BottomEnd)
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.add_a_photo),
                            contentDescription = "Camera Picture",
                            tint = Color.White,
                            modifier = Modifier.size(26.dp)
                        )
                    }
                }
            }
        }

        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text("Alamat", style = MaterialTheme.typography.bodyLarge, color = Color.Black)
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(
                    text = "Jalan Bojongsoang Nomor 1, Kecamatan Sukapura, Kabupaten Bandung, Jawa Barat",
                    color = Color.Black,
                    modifier = Modifier.weight(1f)
                )
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Divider(color = Color.Black,modifier = Modifier.padding(vertical = 8.dp))

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Penjaga", color = Color.Black, modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle add action */ }) {
                    Icon(
                        imageVector = Icons.Default.Add,
                        contentDescription = "Add",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Divider(color = Color.Black, modifier = Modifier.padding(vertical = 3.dp))
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Ujey", color = Color.Black, modifier = Modifier.weight(1f))
                IconButton(onClick = { /* Handle add action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.remove),
                        contentDescription = "Add",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }

            Text(
                "Biaya Parkir",
                style = MaterialTheme.typography.bodyLarge,
                color = Color.Black,
                modifier = Modifier.padding( top = 16.dp)
            )
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Mobil", modifier = Modifier.weight(1f), color = Color.Black)
                Text("Rp5000", color = Color.Black)
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text("Motor", modifier = Modifier.weight(1f), color = Color.Black)
                Text("Rp2000", color = Color.Black)
                IconButton(onClick = { /* Handle edit action */ }) {
                    Icon(
                        painter = painterResource(id = R.drawable.edit),
                        contentDescription = "Edit",
                        tint = Color.Black,
                        modifier = Modifier.size(16.dp)
                    )
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ParkingAreaFormPreview() {
    ParkingAreaForm()
}

@Preview(showBackground = true)
@Composable
fun EditParkingAreaPreview() {
    EditParkingArea()
}