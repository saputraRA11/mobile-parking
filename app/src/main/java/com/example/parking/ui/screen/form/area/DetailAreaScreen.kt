package com.example.parking.ui.screen.form.area

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.parking.ui.content.payment.management.area.DetailManagementAreaContent

@Composable
fun DetailAreaScreen(
    navController: NavHostController = rememberNavController()
){
    DetailManagementAreaContent(
        onClick = {
            navController.navigateUp()
        }
    )
}
