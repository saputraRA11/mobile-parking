package com.example.parking.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Login : Screen("login")
    object Register : Screen("register")

    object Validation : Screen("validation/{path}"){
        fun createRoute(path:String) = "validation/{path}"
    }
    object Home : Screen("home")

    object UpdateArea : Screen("updateArea/{alamat}") {
        fun createRoute(alamat: String) = "updateArea/$alamat"
    }

    object GuardList : Screen("guardList/{phone}") {
        fun createRoute(phone: String) = "guardList/$phone"
    }

    object AddArea : Screen("addArea/{phone}") {
        fun createRoute(phone: String) = "addArea/$phone"
    }

    object AddGuard : Screen("addGuard/{phone}") {
        fun createRoute(phone: String) = "addGuard/$phone"
    }
}