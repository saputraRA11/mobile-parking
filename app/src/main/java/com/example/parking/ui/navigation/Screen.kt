package com.example.parking.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")
    object Home : Screen("home/{username}") {
        fun createRoute(username: String) = "home/$username"
    }
}