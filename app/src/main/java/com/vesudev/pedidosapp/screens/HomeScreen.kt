package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height

import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold

import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color

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

        Scaffold(
            topBar = {HomeTopAppBar()},

            content = { padding ->
                MainAppContent(padding = padding, navController = navController)
            }
        )


    }
}

@Composable
fun MainAppContent(padding: PaddingValues, navController: NavController) {

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
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
            modifier = Modifier.height(150.dp).clip(CircleShape),
            painter = painterResource(id = R.drawable.logo),
            contentDescription = "Logo",

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

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title ={ Text(modifier = Modifier.fillMaxWidth(),text = "Centro de Carnes San Isidro", textAlign = TextAlign.Center)} ,
        colors = topAppBarColors(MaterialTheme.colorScheme.primary) )
}

