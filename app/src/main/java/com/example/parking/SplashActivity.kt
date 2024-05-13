package com.example.parking

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.LaunchedEffect
import com.example.parking.ui.screen.splash.SplashScreen
import com.example.parking.ui.theme.ParkingTheme
import kotlinx.coroutines.delay

@SuppressLint("CustomSplashScreen")
class SplashActivity:ComponentActivity() {
    override fun onCreate(savedInstanceState:Bundle?) {
        super.onCreate(savedInstanceState)
        setContent{
            ParkingTheme {
                SplashScreen(effect = {
                    LaunchedEffect(key1 = true) {
                        delay(2000)
                        startActivity(Intent(this@SplashActivity,MainActivity::class.java))
                    }
                })
            }
        }
    }
}