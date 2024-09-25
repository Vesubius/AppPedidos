package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun LoginScreen(navController: NavController) {
    PedidosAppTheme {
        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {

            Spacer(modifier = Modifier.padding(30.dp))

            //Logo
            Image(
                modifier = Modifier.height(150.dp),
                painter = painterResource(id = R.drawable.logo),
                contentDescription = "Logo"
            )

            Spacer(modifier = Modifier.padding(top = 25.dp))

            //Titulo
            Text(
                text = "Centro de Carnes San Isidro",
                fontWeight = FontWeight.Bold,
                fontSize = 25.sp
            )

            Spacer(modifier = Modifier.padding(top = 50.dp))

            //Campo correo
            Text(text = "Correo Electronico", fontWeight = FontWeight.Normal, fontSize = 20.sp)

            TextField(
                modifier = Modifier.padding(top = 10.dp),
                value = "Correo",
                onValueChange = {})

            Spacer(modifier = Modifier.padding(top = 50.dp))

            //Campo Contraseña
            Text(text = "Contraseña", fontWeight = FontWeight.Normal, fontSize = 20.sp)

            TextField(
                modifier = Modifier.padding(top = 10.dp),
                value = "******",
                onValueChange = {})

            // Texto/Link Para Registrarse
            Row {
                Text(
                    modifier = Modifier.padding(top = 5.dp),
                    text = "Aun no tienes una cuenta?",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp
                )
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 3.dp)
                        .clickable { navController.navigate(route = AppScreens.SignUpScreen.route)},
                    text = "Registrarte",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.Red

                )

            }
            //Boton Iniciar Sesion
            Button(
                modifier = Modifier.padding(top = 20.dp),
                onClick = {}) { Text(text = "Iniciar Sesion") }

            Spacer(modifier = Modifier.padding(10.dp))

            Row {
                Image(
                    modifier = Modifier.padding(end = 10.dp),
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "Line"
                )

                //Iniciar con...
                Text(
                    modifier = Modifier
                        .padding(top = 5.dp, start = 3.dp)
                        .clickable { },
                    text = "Iniciar con...",
                    fontWeight = FontWeight.SemiBold,
                    fontSize = 12.sp,
                    color = Color.Black

                )

                Image(
                    modifier = Modifier.padding(start = 10.dp),
                    painter = painterResource(id = R.drawable.line),
                    contentDescription = "Line"
                )

            }



            Row() {
                Image(
                    modifier = Modifier.clickable { },
                    painter = painterResource(id = R.drawable.google),
                    contentDescription = "Iniciar Sesion con google"
                )
                Spacer(modifier = Modifier.padding(15.dp))

                Image(
                    modifier = Modifier.clickable { },
                    painter = painterResource(id = R.drawable.facebook),
                    contentDescription = "Iniciar Sesion con facebook"
                )
            }
        }
    }
}