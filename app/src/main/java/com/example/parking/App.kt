package com.example.parking

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.parking.ui.navigation.Screen
import com.example.parking.ui.screen.home.HomeScreen
import com.example.parking.ui.screen.login.LoginScreen
import com.example.parking.ui.screen.onboarding.OnboardingScreen
import com.example.parking.ui.screen.register.RegisterScreen
import com.example.parking.ui.screen.validation.ValidationScreen

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
            OnboardingScreen(
                navController
            )
        }

        composable(Screen.Login.route) {
            LoginScreen(
                navController
            )
        }

        composable(Screen.Register.route) {
            RegisterScreen(
                navController
            )
        }

        composable(
            Screen.Validation.route,
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ){
            val phone = it.arguments?.getString("phone") ?: ""
            ValidationScreen(
                navController,
                phone = phone
            )
        }

        composable(
            Screen.Home.route,
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            HomeScreen(
                navController,
                phone = phone
            )
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