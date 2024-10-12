package com.vesudev.pedidosapp.navigation


import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.vesudev.pedidosapp.cartViewModel.CartViewModel
import com.vesudev.pedidosapp.screens.DetailScreen
import com.vesudev.pedidosapp.screens.HomeScreen
import com.vesudev.pedidosapp.screens.LoginScreen
import com.vesudev.pedidosapp.screens.MainAppScreen
import com.vesudev.pedidosapp.screens.ProfileScreen
import com.vesudev.pedidosapp.screens.ShoppingCartScreen
import com.vesudev.pedidosapp.screens.SignUpScreen


@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

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
            ShoppingCartScreen(navController,cartViewModel)
        }

        //Pantalla ProfileScreen
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }

        //Pantalla detalles de Productos
        composable(
            route = "detail/{name}/{uri}",
            arguments = listOf(
                navArgument("name") { type = NavType.StringType },
                navArgument("uri") { type = NavType.StringType },

            )
        ) { backStackEntry ->

            val name = backStackEntry.arguments?.getString("name") ?: "Unknown"
            val imageUrl = backStackEntry.arguments?.getString("uri") ?: ""

            DetailScreen(navController, fileName = name, imageUrl = imageUrl,cartViewModel)
        }
    }
}

