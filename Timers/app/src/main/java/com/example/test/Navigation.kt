package com.example.test

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

import com.example.test.screens.MainScreen
import com.example.test.screens.LeftScreen
import com.example.test.screens.RightScreen
import com.example.test.screens.ScreenRoutes
import com.example.test.timers.FrontScreenViewModel
import com.example.test.timers.LeftScreenViewModel
import com.example.test.timers.RightScreenViewModel

@Composable
fun Navigation(
    leftViewModel: LeftScreenViewModel,
    rightScreenViewModel: RightScreenViewModel,
    frontViewModel: FrontScreenViewModel
) {
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = ScreenRoutes.MainScreen.route) {
        composable(route = ScreenRoutes.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = ScreenRoutes.LeftScreen.route) {
            LeftScreen(name = "LEFT", leftViewModel)
        }
        composable(route = ScreenRoutes.RightScreen.route) {
            RightScreen(name = "RIGHT", rightScreenViewModel)
        }
    }
}

