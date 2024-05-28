package com.example.parking.ui.content

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Scaffold
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowLeft
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.CustomIcon
import com.example.parking.ui.component.OtpInput
import com.example.parking.ui.theme.BluePark

@Composable
fun ValidationContent(
    otpState:MutableState<String> = mutableStateOf(""),
    effect: () -> Unit = {},
    onClick: () -> Unit = {},
    sendClick:() -> Unit = {}
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
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(horizontal = 5.dp, vertical = 80.dp)
                    .fillMaxWidth()
            ) {
                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = onClick,
                    text = "Kirim Otp",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = sendClick,
                    text = "Send Ulang",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
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
                .fillMaxSize()
                .padding(horizontal = 15.dp),
            verticalArrangement = Arrangement.spacedBy(30.dp)
        ) {
            Text(
                text = "Masukkan kode OTP",
                style = MaterialTheme.typography.headlineSmall,
                fontWeight = FontWeight.W500
            )

            OtpInput(
                saveState = otpState,
                maxLimit = 6,
                size = 50,
                fontSize = 40,
                rounded = 15,
                modifier = Modifier,
                repeatCount = 6
            )
        }
    }
}

@Preview(showBackground = true)
@Composable
fun ValidationPreview(){
    ValidationContent()
}