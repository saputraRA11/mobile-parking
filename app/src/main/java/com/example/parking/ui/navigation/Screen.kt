package com.example.parking.ui.navigation

sealed class Screen(val route: String) {
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth/{path}") {
        fun createRoute(path:String  = "none") = "auth/${path}"
    }
    object Home : Screen("home/{path}") {
        fun createRoute(path:String = "none") = "home/${path}"
    }

    object Payment : Screen("payment/{path}") {
        fun createRoute(path:String = "none") = "payment/${path}"
    }

    object Form : Screen("form/{path}/{action}") {
        fun createRoute(path:String = "none",action:String = "none") = "form/${path}/${action}"
    }
}