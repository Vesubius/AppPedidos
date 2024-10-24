package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun OrdersScreen(
    navController: NavController
) {

    PedidosAppTheme {

        Scaffold(

            topBar = { OrdersScreenTopBar(navController) },

            content = { innerPadding ->
                OrdersScreenContent(
                    modifier = Modifier.padding(innerPadding), navController
                )
            })

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Centro de Carnes San Isidro (ADMIN)",
                textAlign = TextAlign.Center
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppScreens.ProfileScreen.route) }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            }
        }
    )
}

@Composable
fun OrdersScreenContent(
    modifier: Modifier,
    navController: NavController,
) {

    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(text = "Bienvenidos a la Vista Admin de la app")

    }
}

