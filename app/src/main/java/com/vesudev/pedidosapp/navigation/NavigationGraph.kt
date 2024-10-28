package com.vesudev.pedidosapp.navigation


import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.FirebaseUser
import com.vesudev.pedidosapp.AdminDB.AdminsDB
import com.vesudev.pedidosapp.ViewModels.CartViewModel

import com.vesudev.pedidosapp.ViewModels.OrderViewModel
import com.vesudev.pedidosapp.screens.DetailScreen
import com.vesudev.pedidosapp.screens.FirstOnBoarding
import com.vesudev.pedidosapp.screens.HomeScreen
import com.vesudev.pedidosapp.screens.LoginScreen
import com.vesudev.pedidosapp.screens.MainAppScreen
import com.vesudev.pedidosapp.screens.OrdersScreen
import com.vesudev.pedidosapp.screens.ProfileScreen
import com.vesudev.pedidosapp.screens.SecondOnBoarding
import com.vesudev.pedidosapp.screens.ShoppingCartScreen
import com.vesudev.pedidosapp.screens.SignUpScreen


@Composable
fun NavigationGraph() {


    // Obtén la instancia de FirebaseAuth
    val auth = FirebaseAuth.getInstance()

    // Verifica el usuario actual
    val currentUser = auth.currentUser

    val urlsEmbutidos = remember { mutableStateListOf("") }
    val urlsCarnes = remember { mutableStateListOf("") }

    val navController = rememberNavController()
    val cartViewModel: CartViewModel = viewModel()

    val orderViewModel: OrderViewModel = viewModel()

    CurrentUserVerify(navController, currentUser)

    NavHost(navController = navController, startDestination = AppScreens.FirstOnBoarding.route) {

        //Pantalla FirstOnBoarding
        composable(route = AppScreens.FirstOnBoarding.route){
            FirstOnBoarding(navController)
        }

        //Pantalla SecondOnBoarding
        composable(route = AppScreens.SecondOnBoarding.route){
            SecondOnBoarding(navController)
        }

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

        //Pantalla Principal
        composable(route = AppScreens.MainAppScreen.route) {
            MainAppScreen(navController, urlsEmbutidos, urlsCarnes,cartViewModel)

        }

        //Pantalla ShoppingCartScreen
        composable(route = AppScreens.ShoppingCartScreen.route) {
            ShoppingCartScreen(navController, cartViewModel,orderViewModel)
        }

        //Pantalla ProfileScreen
        composable(route = AppScreens.ProfileScreen.route) {
            ProfileScreen(navController)
        }

        //Pantalla de Pedidos (ONLY ADMINS)
        composable(route = AppScreens.OrdersScreen.route) {
            OrdersScreen(navController)
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

            DetailScreen(navController, fileName = name, imageUrl = imageUrl, cartViewModel)
        }


    }



}

@Composable
fun CurrentUserVerify(
    navController: NavHostController,
    currentUser: FirebaseUser?
) {
    LaunchedEffect(currentUser) {
        when {
            //Si la Lista de Admins Contiene El Usuario Actual Significa que es Admin
           AdminsDB.User.UserList.contains(currentUser?.email?.lowercase()) -> {
                //Muestra la pantalla principal de admin
                if (navController.currentDestination?.route != AppScreens.OrdersScreen.route) {
                    navController.navigate(AppScreens.OrdersScreen.route) {
                        popUpTo(AppScreens.FirstOnBoarding.route) { inclusive = true }
                    }
                }
            }

            currentUser != null -> {
                // Usuario autenticado, navegar a la pantalla principal
                if (navController.currentDestination?.route != AppScreens.MainAppScreen.route) {
                    navController.navigate(AppScreens.MainAppScreen.route) {
                        popUpTo(AppScreens.FirstOnBoarding.route) { inclusive = true }
                    }
                }
            }
            else -> {
                // Usuario no autenticado
                if (navController.currentDestination?.route != AppScreens.FirstOnBoarding.route &&
                    navController.currentDestination?.route != AppScreens.LoginScreen.route) {
                    // Si no estás en Onboarding ni en Login, ir a Onboarding
                    navController.navigate(AppScreens.FirstOnBoarding.route) {
                        popUpTo(AppScreens.FirstOnBoarding.route) { inclusive = true }
                    }
                }
            }
        }
    }
}

