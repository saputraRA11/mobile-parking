package com.example.parking.ui.content

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.screen.Form.AddAreaForm

@Composable
fun AddAreaContent(
    backClick: () -> Unit = {},
    submitClick:() -> Unit = {},
    addAreaForm: MutableState<AddAreaForm> = mutableStateOf(AddAreaForm())
){
    val isFormFilled by remember {
        derivedStateOf {
            addAreaForm.value.namaArea.value.text.isNotEmpty() &&
                    addAreaForm.value.jalanLengkap.value.text.isNotEmpty() &&
                    addAreaForm.value.biayaMobil.value.text.isNotEmpty() &&
                    addAreaForm.value.biayaMotor.value.text.isNotEmpty()
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
                IconButton(onClick = backClick) {
                    Icon(
                        imageVector = Icons.AutoMirrored.Filled.ArrowBack,
                        contentDescription = "Back",
                        tint = Color.White,
                        modifier = Modifier.size(53.dp)
                    )
                }
                Spacer(modifier = Modifier.weight(1f))
                if (isFormFilled) {
                    IconButton(onClick = submitClick) {
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
//                Box(
//                    modifier = Modifier
//                        .size(80.dp)
//                        .background(Color(0xFF0D47A1), shape = CircleShape),
//                    contentAlignment = Alignment.Center
//                ) {
//                    IconButton(onClick = { /* Handle camera action */ }) {
//                        Image(
//                            painter = painterResource(id = R.drawable.add_a_photo),
//                            contentDescription = "Camera Picture",
//                            modifier = Modifier.size(36.dp)
//                        )
//                    }
//                }
//
//                Spacer(modifier = Modifier.width(16.dp))

                TextField(
                    value = addAreaForm.value.namaArea.value,
                    onValueChange = { addAreaForm.value.namaArea.value = it },
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
                value = addAreaForm.value.jalanLengkap.value,
                onValueChange = { addAreaForm.value.jalanLengkap.value = it },
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
                value = addAreaForm.value.biayaMobil.value,
                onValueChange = { addAreaForm.value.biayaMobil.value = it },
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
                value = addAreaForm.value.biayaMotor.value,
                onValueChange = { addAreaForm.value.biayaMotor.value = it },
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

@Preview(
    showBackground = true
)
@Composable
fun showAddAreaContent(){
    AddAreaContent()
}