package com.example.vendigo.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.vendigo.presentation.screens.OtpVerifyScreen
import com.example.vendigo.presentation.screens.UserPhnoInputScreen

@Composable
fun VendigoNavigation() {
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = VendigoScreens.UserPhnoInputScreen.name){

        composable(VendigoScreens.UserPhnoInputScreen.name )
         {

            UserPhnoInputScreen(navController = navController)
        }

        composable(VendigoScreens.OtpVerifyScreen.name) {
            OtpVerifyScreen(navController = navController)
        }

        }

    }
