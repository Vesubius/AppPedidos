package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.Logo
import com.vesudev.pedidosapp.reusable.OpenFacebookButton
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun HomeScreen(navController: NavController) {
    PedidosAppTheme {

        Scaffold(
            topBar = { HomeTopAppBar() },

            content = { padding ->
                MainAppContent(padding = padding, navController = navController)
            }
        )


    }
}

@Composable
fun MainAppContent(padding: PaddingValues, navController: NavController) {

    background()

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(padding),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {


        Logo()

        HomeLogInButton(navController)

        SignUpButton(navController)

        //By VesuDev Text
        OpenFacebookButton()

    }

}

@Composable
private fun background() {
    Image(
        modifier = Modifier.fillMaxSize(),
        painter = painterResource(R.drawable.homescreenbg), contentScale = ContentScale.Crop,
        contentDescription = null
    )
}

@Composable
fun SignUpButton(navController: NavController) {
    Button(onClick = { navController.navigate(route = AppScreens.SignUpScreen.route) }) {
        Text(
            "Registrarse"
        )
    }
}

@Composable
fun HomeLogInButton(navController: NavController) {
    Button(onClick = { navController.navigate(route = AppScreens.LoginScreen.route) }) {
        Text(
            "Iniciar Sesion"
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeTopAppBar() {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Centro de Carnes San Isidro",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary)
    )
}

