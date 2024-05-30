package com.example.parking.ui.screen.payment.guard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.guard.PaymentGenerateQrContent
import com.example.parking.ui.content.payment.guard.type.PaymentCashContent
import com.example.parking.ui.navigation.Screen

@Composable()
fun PaymentGenerateQrScreen(
    navController: NavHostController
){
    PaymentGenerateQrContent(
        backClick = {
            navController.navigateUp()
        },
        generateQr = {
            navController.navigate(Screen.Payment.createRoute("qr"))
        }
    )
}