package com.example.parking.ui.content.payment.management.guard

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
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
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.R
import com.example.parking.ui.screen.form.AddGuardForm
import com.example.parking.ui.screen.home.Area

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun AddGuardContent(
    backClick: () -> Unit = {},
    submitClick: () -> Unit = {},
    addGuardForm: MutableState<AddGuardForm> = mutableStateOf(AddGuardForm()),
    listArea: MutableList<Area> = mutableListOf()
){
    val expanded = remember { mutableStateOf(false) }
    val isFormFilled by remember {
        derivedStateOf {
            addGuardForm.value.guardPhone.value.text.isNotEmpty() &&
                    addGuardForm.value.guardName.value.text.isNotEmpty() &&
                    addGuardForm.value.nik.value.text.isNotEmpty() &&
                    addGuardForm.value.selectedArea.value.isNotEmpty()
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
                        imageVector = Icons.Default.ArrowBack,
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
                TextField(
                    value = addGuardForm.value.guardPhone.value,
                    onValueChange = { addGuardForm.value.guardPhone.value = it },
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
                value = addGuardForm.value.guardName.value,
                onValueChange = { addGuardForm.value.guardName.value = it },
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
                value = addGuardForm.value.nik.value,
                onValueChange = { addGuardForm.value.nik.value = it },
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
                    value = addGuardForm.value.selectedArea.value,
                    onValueChange = { addGuardForm.value.selectedArea.value = it },
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
                    listArea.map {
                        area ->
                        DropdownMenuItem(
                            text = { Text(text = area.name) },
                            onClick = {
                                addGuardForm.value.selectedArea.value = area.id
                                expanded.value = false
                            }
                        )
                    }
                }
            }
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun AddGuardPreview(){
    AddGuardContent()
}
