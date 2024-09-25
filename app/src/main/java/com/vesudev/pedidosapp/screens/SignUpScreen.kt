package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun SignUpScreen(navController: NavController) {
    PedidosAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {
            //Campo usuario
            Box {
                Column {
                    Text(text = "Usuario")
                    TextField(value = "", onValueChange = { })
                }
            }
            //Campo Nombre Completo
            Box {
                Column {
                    Text(text = "Nombre Completo")
                    TextField(value = "", onValueChange = { })
                }
            }
            //Campo Numero
            Box {
                Column {
                    Text(text = "Numero")
                    TextField(value = "", onValueChange = { })
                }
            }
            //Campo Correo
            Box {
                Column {
                    Text(text = "Correo")
                    TextField(value = "", onValueChange = { })
                }
            }
            //Campo Contraseña
            Box {
                Column {
                    Text(text = "Contraseña")
                    TextField(value = "", onValueChange = { })
                }
            }
            //Campo Confirmar Contraseña
            Box {
                Column {
                    Text(text = " Confirmar Contraseña")
                    TextField(value = "", onValueChange = { })
                }
            }

            Button(onClick = {navController.navigate(route = AppScreens.LoginScreen.route)}) { Text(text = "Registrarse") }
        }
    }
}

