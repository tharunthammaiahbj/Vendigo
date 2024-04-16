package com.example.vendigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vendigo.screens.OtpVerifyScreen
import com.example.vendigo.screens.PhoneVerifyScreen

@Composable
fun VendigoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = VendigoScreens.PhoneVerifyScreen.name){

        composable(VendigoScreens.PhoneVerifyScreen.name) {
            PhoneVerifyScreen(navController = navController)
        }

        composable(VendigoScreens.OtpVerifyScreen.name) {
            OtpVerifyScreen(navController = navController)
        }

        }

    }
