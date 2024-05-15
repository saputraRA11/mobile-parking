package com.example.parking.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Validation : Screen("validation/{phone}") {
        fun createRoute(phone: String) = "validation/$phone"
    }
    object Home : Screen("home/{phone}") {
        fun createRoute(phone: String) = "home/$phone"
    }
}