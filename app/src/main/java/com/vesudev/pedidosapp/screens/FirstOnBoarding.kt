package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
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
import androidx.compose.material3.MaterialTheme

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun FirstOnBoarding(navController: NavController) {
    PedidosAppTheme {
        Column(
            modifier = Modifier
                .fillMaxSize()
                .background(MaterialTheme.colorScheme.background),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceBetween
        ) {

            Image(
                modifier = Modifier.padding(top = 150.dp),
                painter = painterResource(id = R.drawable.meat),
                contentDescription = "imagen de Chuleta"
            )

            //Texto
            Box {
                Column(
                    modifier = Modifier.padding(bottom = 150.dp),
                    verticalArrangement = Arrangement.Center,
                    horizontalAlignment = Alignment.CenterHorizontally
                ) {
                    Text(text = "Pide una Orden", fontWeight = FontWeight.Bold, fontSize = 25.sp)
                    Spacer(modifier = Modifier.height(15.dp))
                    Text(text = "Ordena de Forma sencilla y rapida tus pedidos", fontSize = 20.sp)
                }
            }

            Box(modifier = Modifier.padding(bottom = 70.dp)) {
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.SpaceEvenly,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Button(onClick = {
                        navController.navigate(route = AppScreens.HomeScreen.route) {
                            popUpTo(AppScreens.FirstOnBoarding.route) { inclusive = true }
                        }
                    }) {
                        Text(
                            text = "Saltar",
                            fontSize = 17.sp
                        )
                    }

                    Box() {
                        Row(
                            horizontalArrangement = Arrangement.Center,
                            verticalAlignment = Alignment.CenterVertically
                        ) {
                            Image(
                                contentScale = ContentScale.Crop,
                                modifier = Modifier.height(12.dp),
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "marcador actual de Onboarding",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )

                            Image(
                                modifier = Modifier.height(13.dp),
                                painter = painterResource(id = R.drawable.circle),
                                contentDescription = "linea siguiente",
                                colorFilter = ColorFilter.tint(MaterialTheme.colorScheme.primary)
                            )
                        }
                    }

                    Button(onClick = { navController.navigate(route = AppScreens.SecondOnBoarding.route) }) {
                        Text(
                            text = "Siguiente",
                            fontSize = 17.sp
                        )
                    }


                }
            }
        }
    }
}