package com.example.parking.ui.screen.payment

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.parking.ui.screen.NotFoundPage
import com.example.parking.ui.screen.payment.guard.PaymentGenerateQrScreen
import com.example.parking.ui.screen.payment.guard.PaymentGuardScreen
import com.example.parking.ui.screen.payment.guard.type.PaymentCashScreen
import com.example.parking.ui.screen.payment.guard.type.PaymentQrScreen
import com.example.parking.ui.screen.payment.main.PaymentScreen
import com.example.parking.ui.screen.payment.management.PaymentManagementScreen

@RequiresApi(Build.VERSION_CODES.O)
@Composable
fun PaymentHostNavigation(
    navController: NavHostController,
    path:String
) {
    when(path) {
        "user" -> PaymentScreen(navController)
        "guard" -> PaymentGuardScreen(navController)
        "owner" -> PaymentManagementScreen(navController)
        "generate" -> PaymentGenerateQrScreen(navController)
        "qr" -> PaymentQrScreen(navController)
        "cash" -> PaymentCashScreen(navController)
        else -> NotFoundPage()
    }
}