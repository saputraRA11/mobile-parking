package com.example.parking.ui.screen.home

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.screen.NotFoundPage
import com.example.parking.ui.screen.home.guard.HomeGuardScreen
import com.example.parking.ui.screen.home.main.HomeScreen
import com.example.parking.ui.screen.home.management.HomeManagementScreen

@Composable
fun HomeHostNavigation(
    navController: NavHostController,
    path:String) {
    when(path) {
        "user" -> HomeScreen(navController)
        "guard" -> HomeGuardScreen(navController)
        "owner" -> HomeManagementScreen(navController)
        else -> NotFoundPage()
    }
}