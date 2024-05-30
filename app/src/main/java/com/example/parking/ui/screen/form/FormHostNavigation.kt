package com.example.parking.ui.screen.form

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.screen.NotFoundPage
import com.example.parking.ui.screen.form.area.AddAreaFormScreen
import com.example.parking.ui.screen.form.area.DetailAreaScreen
import com.example.parking.ui.screen.form.area.UpdateParkingAreaScreen
import com.example.parking.ui.screen.form.guard.AddGuardFormScreen
import com.example.parking.ui.screen.form.guard.ListGuardScreen

@Composable
fun FormHostNavigation(
    navController: NavHostController,
    path:String,action:String) {
    when(path) {
        "area" -> {
            when(action){
                "add" -> AddAreaFormScreen(navController)
                "update" -> UpdateParkingAreaScreen(navController)
                "detail" -> DetailAreaScreen(navController)
                else -> NotFoundPage()
            }
        }
        "guard" -> {
            when(action){
                "add" -> AddGuardFormScreen(navController)
                "list" -> ListGuardScreen(navController)
                else -> NotFoundPage()
            }
        }
        else -> NotFoundPage()
    }
}