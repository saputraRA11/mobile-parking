package com.example.parking.ui.screen.payment.guard.type

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.guard.type.PaymentCashContent
import com.example.parking.ui.content.payment.guard.type.PaymentQrContent

@Composable()
fun PaymentQrScreen(
    navController: NavHostController
){
    PaymentQrContent(
        backClick = {
            navController.navigateUp()
        }
    )
}