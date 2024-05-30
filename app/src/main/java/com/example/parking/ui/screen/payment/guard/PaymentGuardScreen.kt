package com.example.parking.ui.screen.payment.guard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.guard.PaymentGuardContent
import com.example.parking.ui.navigation.Screen

@Composable
fun PaymentGuardScreen(
    navController: NavHostController
){
    PaymentGuardContent(
        homeClick = {
            navController.navigateUp()
        },
        detailPayment = {
            id,type ->
            when(type) {
                "qr" -> navController.navigate(Screen.Payment.createRoute("generate"))
                "cash" -> navController.navigate(Screen.Payment.createRoute("cash"))
                else  -> navController.navigate(Screen.Payment.createRoute("none"))
            }
        }
    )
}