package com.example.parking.ui.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Greeting(
    modifier: Modifier = Modifier,
    isRegister:Boolean = true
) {
    Column (
        modifier = modifier,
        verticalArrangement = Arrangement.spacedBy(10.dp)
    ){
        Text(
            text = "Halo Pengguna Easy Park~",
            style = MaterialTheme.typography.headlineSmall,
            fontWeight = FontWeight.W500
        )
        Text(
            text = "${if(isRegister) "Daftar" else "Masuk"} dapat kamu lakukan dengan mudah dan cepat hanya dalam beberapa langkah",
            style = MaterialTheme.typography.bodyLarge,
            fontWeight = FontWeight.W300,
            softWrap = true
            )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview(){
    Greeting()
}