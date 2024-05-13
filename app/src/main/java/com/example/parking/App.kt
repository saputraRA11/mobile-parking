package com.example.parking

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.onboarding.OnboardingScreen

@Composable
fun ParkingApp(
    modifier: Modifier = Modifier,
    navController:NavHostController = rememberNavController()
) {
    NavHost(
        navController = navController,
        startDestination = Screen.Onboarding.route
    ) {
        composable(Screen.Onboarding.route) {
            OnboardingScreen()
        }

        composable(Screen.Home.route) {

        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ParkingAppPreview(){
    ParkingApp()
}