package com.vesudev.pedidosapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign


import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase

import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.AppTittle

import com.vesudev.pedidosapp.reusable.GmailTextField
import com.vesudev.pedidosapp.reusable.Logo
import com.vesudev.pedidosapp.reusable.PasswordTextField
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun LoginScreen(navController: NavController) {
    PedidosAppTheme {
        Scaffold(

            topBar = { LoginTopAppBar() },

            content = { innerPadding ->
                Column(Modifier.padding(innerPadding)) {
                    LoginScreenContent(navController)
                }
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LoginTopAppBar() {
    TopAppBar(
        title = {
            Text(
                text = "Iniciar Sesion",
                textAlign = TextAlign.Center,
                modifier = Modifier.fillMaxWidth(),
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun LoginScreenContent(navController: NavController) {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {
        //Variables
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        val context = LocalContext.current


        Logo()

        AppTittle()

        GmailTextField(email = email, onEmailChange = { email = it })

        PasswordTextField(
            password = password,
            labeltext = "Contraseña",
            onPasswordChange = { password = it })

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
                    .clickable { navController.navigate(route = AppScreens.SignUpScreen.route) },
                text = "Registrarte",
                fontWeight = FontWeight.SemiBold,
                fontSize = 12.sp,
                color = Color.Red

            )

        }

        LoginLogInButton(email, password, context, navController)

        LoginOptions()
    }
}

@Composable
fun LoginOptions() {

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

    Row {
        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.google),
            contentDescription = "Iniciar Sesion con google"
        )


        Image(
            modifier = Modifier.clickable { },
            painter = painterResource(id = R.drawable.facebook),
            contentDescription = "Iniciar Sesion con facebook"
        )
    }
}

@Composable
fun LoginLogInButton(
    email: String,
    password: String,
    context: Context,
    navController: NavController
) {
    //Boton Iniciar Sesion
    Button(
        modifier = Modifier.padding(top = 20.dp),
        onClick = {
            if (email.isNotEmpty() and password.isNotEmpty()) {
                iniciarSesion(email, password, context, navController)
            } else {
                Toast.makeText(context, "Los campos estan vacios", Toast.LENGTH_SHORT)
                    .show()
            }

        }) {
        Text(
            text = "Iniciando Sesion",
            fontSize = 20.sp,
            color = MaterialTheme.colorScheme.onPrimary
        )
    }
}


fun iniciarSesion(
    email: String,
    password: String,
    context: Context,
    navController: NavController
) {
    val auth = Firebase.auth
    val currentUser = auth.currentUser


    auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
        if (task.isSuccessful) {
            Toast.makeText(context, "Inicio de Sesion exitoso", Toast.LENGTH_LONG).show()

            navController.navigate(route = AppScreens.MainAppScreen.route) {
                popUpTo(AppScreens.FirstOnBoarding.route) { inclusive = true }
            }

        } else {
            Toast.makeText(context, "Inicio de Sesion Fallido:${task.exception}", Toast.LENGTH_LONG)
                .show()
            navController.navigate(route = AppScreens.LoginScreen.route)

        }
    }
}


