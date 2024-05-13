package com.example.parking.ui.screen.onboarding

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.parking.ui.component.ButtonCircle
import com.example.parking.ui.component.ImageParkName
import com.example.parking.ui.component.SliderOnboarding
import com.example.parking.ui.theme.BluePark

@Composable
fun OnboardingScreen(
    modifier:Modifier = Modifier
) {
    Scaffold(
        modifier = modifier
            .fillMaxSize()
            ,
        topBar = {
            Column(
                modifier = modifier
                .padding(20.dp)
            ) {
                ImageParkName()
            }
        },
        bottomBar = {
            Column(
                modifier = Modifier
                    .padding(20.dp)
                    .fillMaxWidth()
            ) {
                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    text = "Masuk",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = BluePark)
                )

                ButtonCircle(
                    modifier = Modifier.fillMaxWidth(),
                    onClick = {},
                    text = "Pengguna Baru? Buat akun",
                    backgroundColor = ButtonDefaults.buttonColors(containerColor = Color.White, contentColor = BluePark),
                    isOutlined = true,
                    border = BorderStroke(1.dp, BluePark)
                )
            }
        }
    ) {
        innerPadding ->
        Column (
            modifier = Modifier
                .padding(innerPadding)
                .fillMaxSize(),
            verticalArrangement = Arrangement.Center,
            horizontalAlignment = Alignment.CenterHorizontally
        ){
            SliderOnboarding(modifier = modifier)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun OnboardingPreview() {
    OnboardingScreen()
}