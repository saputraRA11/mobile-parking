package com.example.parking.ui.screen.payment.guard.type

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.guard.type.PaymentCashContent

@Composable()
fun PaymentCashScreen(
    navController: NavHostController
){
    PaymentCashContent(
        backClick = {
            navController.navigateUp()
        }
    )
}