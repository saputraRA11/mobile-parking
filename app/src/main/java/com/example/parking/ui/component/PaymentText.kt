package com.example.parking.ui.component

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.sp

@Composable
fun PaymentText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pembayaran Non-Tunai",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Text(
            text = "Buat kode pembayaran untuk pelanggan",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}


@Composable
fun PaymentQrText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Pembayaran Non-Tunai",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 24.sp
        )
        Text(
            text = "Tunjukkan kode di bawah ini ke pelanggan",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
    }
}

@Composable
fun BillText(modifier: Modifier = Modifier) {
    Column(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "TAGIHAN SEBESAR",
            textAlign = TextAlign.Center,
            fontSize = 16.sp
        )
        Text(
            text = "Rp5.000",
            fontWeight = FontWeight.Bold,
            textAlign = TextAlign.Center,
            fontSize = 32.sp
        )
    }
}

@Composable
fun CashText(modifier: Modifier = Modifier) {
    Text(
        text = "Pembayaran Tunai",
        fontWeight = FontWeight.Bold,
        textAlign = TextAlign.Center,
        fontSize = 24.sp
    )
}

@Preview(showBackground = true)
@Composable
private fun PaymentTextPreview() {
    PaymentText()
}

@Preview(showBackground = true)
@Composable
private fun PaymentQrTextPreview() {
    PaymentQrText()
}

@Preview(showBackground = true)
@Composable
private fun BillTextPreview() {
    BillText()
}

@Preview(showBackground = true)
@Composable
private fun CashTextPreview() {
    CashText()
}