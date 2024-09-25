package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun HomeScreen(navController: NavController) {
    PedidosAppTheme() {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = (Alignment.CenterHorizontally)
        ) {
            Spacer(modifier = Modifier.padding(50.dp))

            Image(painter = painterResource(id = R.drawable.logo), contentDescription = "Logo")

            Spacer(modifier = Modifier.padding(50.dp))

            Button(onClick = { navController.navigate(route = AppScreens.LoginScreen.route) }) { Text("Iniciar Sesion") }

            Spacer(modifier = Modifier.padding(50.dp))

            Button(onClick = {navController.navigate(route = AppScreens.SignUpScreen.route)}) { Text("Registrarse") }

            Spacer(modifier = Modifier.padding(80.dp))

            Box {
                Row {
                    Spacer(modifier = Modifier.padding(120.dp))
                    Text(text = "By VesuDev")
                }
            }
        }
    }
}
