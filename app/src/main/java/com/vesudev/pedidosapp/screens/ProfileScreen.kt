package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.AppTittle
import com.vesudev.pedidosapp.reusable.signOut

@Composable
fun ProfileScreen(navController: NavController) {
    val auth = Firebase.auth

    val user = auth.currentUser

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text(
            modifier = Modifier.fillMaxWidth(),
            textAlign = TextAlign.Center,
            //Si el displayName Es null Muesta usuario
            text = "Bienvenido ${user?.displayName ?: "Usuario"}",
            fontWeight = FontWeight.Bold,
            fontSize = 20.sp
        )

        Button(onClick = {
            signOut()
            navController.navigate(route = AppScreens.LoginScreen.route) {
                popUpTo(AppScreens.HomeScreen.route) { inclusive = true }
            }
        }) { Text(text = "Cerrar Sesion") }
    }
}