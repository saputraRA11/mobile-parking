package com.example.parking.ui.screen.payment.management

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.content.payment.management.PaymentManagementContent
import com.example.parking.ui.navigation.Screen

@Composable
fun PaymentManagementScreen(
    navController: NavHostController,
){
    PaymentManagementContent(
        homeClick = {
            navController.navigateUp()
        },
        detailAreaClick = {
            id ->
            navController.navigate(Screen.Form.createRoute("area","detail"))
        }
    )
}