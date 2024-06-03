package com.example.parking

import android.os.Build
import androidx.annotation.RequiresApi
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
import com.example.parking.ui.screen.auth.AuthHostNavigation
import com.example.parking.ui.screen.onboarding.OnboardingScreen
import com.example.parking.ui.screen.form.FormHostNavigation
import com.example.parking.ui.screen.home.HomeHostNavigation
import com.example.parking.ui.screen.payment.PaymentHostNavigation

@RequiresApi(Build.VERSION_CODES.O)
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

        composable(
            Screen.Auth.route,
            arguments = listOf(navArgument("path") {
                type = NavType.StringType
            })
        ) {
            val path = it.arguments?.getString("path") ?: "none"
            AuthHostNavigation(navController, path)
        }

        composable(
            Screen.Home.route,
            arguments = listOf(navArgument("path") {
                type = NavType.StringType
            })
        ) {
            val path = it.arguments?.getString("path") ?: "none"
            HomeHostNavigation(navController,path)
        }

        composable(
            Screen.Payment.route,
            arguments = listOf(navArgument("path") {
                type = NavType.StringType
            })
        ) {
            val path = it.arguments?.getString("path") ?: "none"
            PaymentHostNavigation(navController,path)
        }

        composable(
            Screen.Form.route,
            arguments = listOf(
                navArgument("path") { type = NavType.StringType },
                navArgument("action") { type = NavType.StringType }
            )
        ) {
            val path = it.arguments?.getString("path") ?: "none"
            val action = it.arguments?.getString("action") ?: "none"
            FormHostNavigation(navController,path,action)
        }


    }
}

@RequiresApi(Build.VERSION_CODES.O)
@Preview(
    showBackground = true
)
@Composable
fun ParkingAppPreview() {
    ParkingApp()
}