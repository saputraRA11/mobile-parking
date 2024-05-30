package com.example.parking.ui.screen.payment.main

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.main.PaymentContent

@Composable
fun PaymentScreen(
    navController: NavHostController
    ){
    PaymentContent(
        homeClick = {
            navController.navigateUp()
        }
    )
}