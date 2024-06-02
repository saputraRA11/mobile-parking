package com.example.parking.ui.screen.form.guard

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.ui.content.payment.management.guard.DetailGuardListContent

@Composable
fun ListGuardScreen(
    navController: NavHostController = rememberNavController(),
) {
    DetailGuardListContent(
        onClick = {
            navController.navigateUp()
        }
    )
}