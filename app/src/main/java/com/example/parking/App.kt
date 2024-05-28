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
import com.example.parking.ui.screen.Form.AddAreaFormScreen
import com.example.parking.ui.screen.Form.AddGuardFormScreen
import com.example.parking.ui.screen.Form.EditParkingAreaScreen
import com.example.parking.ui.screen.GuardList.ManagementGuardScreen
import com.example.parking.ui.screen.home.HomeScreen
import com.example.parking.ui.screen.login.LoginScreen
import com.example.parking.ui.screen.onboarding.OnboardingScreen
import com.example.parking.ui.screen.register.RegisterScreen
import com.example.parking.ui.screen.validation.ValidationScreen

@Composable
fun ParkingApp(
    modifier: Modifier = Modifier,
    navController: NavHostController = rememberNavController()
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
                navController = navController
            )
        }

        composable(
            Screen.Validation.route,
            arguments = listOf(navArgument("path") {
                type = NavType.StringType
            })
        ) {
            val path = it.arguments?.getString("path") ?: "login"
            ValidationScreen(
                navController,
                path = path
            )
        }

        composable(
            Screen.Home.route
        ) {
            HomeScreen(navController)
        }

        composable(
            Screen.UpdateArea.route,
            arguments = listOf(navArgument("alamat") {
                type = NavType.StringType
            })
        ) {
            val alamat = it.arguments?.getString("alamat") ?: ""
            EditParkingAreaScreen(alamat = alamat, navController = navController)
        }

        composable(
            Screen.GuardList.route,
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            ManagementGuardScreen(phone = phone, navController = navController)
        }

        composable(
            Screen.AddArea.route,
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            AddAreaFormScreen(phone = phone, navController = navController)
        }

        composable(
            Screen.AddGuard.route,
            arguments = listOf(navArgument("phone") {
                type = NavType.StringType
            })
        ) {
            val phone = it.arguments?.getString("phone") ?: ""
            AddGuardFormScreen(phone = phone, navController = navController)
        }
    }
}

@Preview(
    showBackground = true
)
@Composable
fun ParkingAppPreview() {
    ParkingApp()
}