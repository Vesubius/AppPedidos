package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button

import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun HomeScreen(navController: NavController) {
    PedidosAppTheme {

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {

        //boton de prueba
            Button(onClick = { navController.navigate(route = AppScreens.MainAppScreen.route) }) {
                Text(
                    "App Test"
                )
            }


            Image(
                modifier = Modifier.height(150.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )



            Button(onClick = { navController.navigate(route = AppScreens.LoginScreen.route) }) {
                Text(
                    "Iniciar Sesion"
                )
            }



            Button(onClick = { navController.navigate(route = AppScreens.SignUpScreen.route) }) {
                Text(
                    "Registrarse"
                )
            }

            Text(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 10.dp),
                textAlign = TextAlign.End,
                text = "By Vesudev"
            )


        }


    }
}

