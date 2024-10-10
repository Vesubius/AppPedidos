package com.vesudev.pedidosapp.navigation


import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.vesudev.pedidosapp.screens.HomeScreen
import com.vesudev.pedidosapp.screens.LoginScreen
import com.vesudev.pedidosapp.screens.MainAppScreen
import com.vesudev.pedidosapp.screens.ProfileScreen
import com.vesudev.pedidosapp.screens.ShoppingCartScreen
import com.vesudev.pedidosapp.screens.SignUpScreen


@Composable
fun AppNavigation() {
    var navController = rememberNavController()
    NavHost(navController = navController, startDestination = AppScreens.HomeScreen.route) {
        //Pantalla homeScreen
        composable(route = AppScreens.HomeScreen.route) {
            HomeScreen(navController)
        }

        //Pantalla LoginScreen
        composable(route = AppScreens.LoginScreen.route) {
            LoginScreen(navController)
        }

        //Pantalla SignUpScreen
        composable(route = AppScreens.SignUpScreen.route) {
            SignUpScreen(navController)
        }

        composable(route = AppScreens.MainAppScreen.route) {
            MainAppScreen(navController)

        }

        //Pantalla ShoppingCartScreen
        composable(route = AppScreens.ShoppingCartScreen.route) {
            ShoppingCartScreen(navController)
        }

        //Pantalla ProfileScreen
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }
    }
}

