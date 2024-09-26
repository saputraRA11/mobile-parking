package com.example.parking.ui.screen.splash

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.parking.R
import com.example.parking.ui.theme.BluePark
import com.example.parking.ui.theme.ParkingTheme

@Composable
fun SplashScreen(
    modifier:Modifier = Modifier,
    effect: @Composable () -> Unit
) {
    effect()
    Column (
        modifier = modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ){
        Image(
            painter = painterResource(id = R.drawable.logo_parking),
            contentDescription = null,
            modifier = Modifier.size(300.dp)
        )
        Text(
            text = "Easy Park",
            fontSize = 50.sp,
            color = BluePark,
            fontWeight = FontWeight.Bold,
            )
    }
}

@Preview(
    showBackground = true
)
@Composable
fun SplashScreenPreview(){
    ParkingTheme {
        SplashScreen(effect = {})
    }
}