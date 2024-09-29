package com.vesudev.pedidosapp.navigation

open class AppScreens (val route: String){
    object HomeScreen:AppScreens("HomeScreen")
    object LoginScreen:AppScreens("LoginScreen")
    object SignUpScreen:AppScreens("SignUpScreen")
    object BasicAppScreen:AppScreens( "BasicAppScreen")

}