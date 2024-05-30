package com.example.parking.ui.screen.auth

import androidx.compose.runtime.Composable
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import com.example.parking.ui.screen.NotFoundPage
import com.example.parking.ui.screen.auth.pages.LoginScreen
import com.example.parking.ui.screen.auth.pages.RegisterScreen
import com.example.parking.ui.screen.auth.pages.ValidationScreen

@Composable
fun AuthHostNavigation(navController: NavHostController, path:String) {
    when(path) {
        "login" -> LoginScreen(navController)
        "register" -> RegisterScreen(navController)
        "validation" -> ValidationScreen(navController)
        else -> NotFoundPage()
    }
}