package com.vesudev.pedidosapp.navigation

open class AppScreens (val route: String){

    object FirstOnBoarding:AppScreens("FirstOnBoarding")
    object SecondOnBoarding:AppScreens("SecondOnBoarding")
    object HomeScreen:AppScreens("HomeScreen")
    object LoginScreen:AppScreens("LoginScreen")
    object SignUpScreen:AppScreens("SignUpScreen")
    object MainAppScreen:AppScreens("MainAppScreen")
    object ShoppingCartScreen:AppScreens("ShoppingCartScreen")
    object ProfileScreen:AppScreens("ProfileScreen")
    object OrdersScreen:AppScreens("OrdersScreen")

}