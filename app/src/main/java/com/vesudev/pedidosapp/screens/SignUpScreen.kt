package com.vesudev.pedidosapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun SignUpScreen(navController: NavController) {
    PedidosAppTheme {

        //Variables
        var email by remember { mutableStateOf("") }
        var password by remember { mutableStateOf("") }
        var confirmpassword by remember { mutableStateOf("") }
        val context = LocalContext.current

        Column(
            modifier = Modifier.fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.SpaceEvenly
        ) {


            //Campo Correo
            Box {
                Column {
                    Text(text = "Correo")
                    OutlinedTextField(
                        value = email,
                        onValueChange = { email = it },
                        placeholder = { Text(text = "example@domain.com") })
                }
            }

            //Campo Contraseña
            Box {
                Column {
                    Text(text = "Contraseña")
                    OutlinedTextField(
                        value = password,
                        onValueChange = { password = it },
                        placeholder = { Text(text = "******") })
                }
            }

            //Campo Confirmar Contraseña
            Box {
                Column {
                    Text(text = " Confirmar Contraseña")
                    OutlinedTextField(
                        value = confirmpassword,
                        onValueChange = { confirmpassword = it },
                        placeholder = { Text(text = "******") })
                }
            }

            Button(onClick = {
                if (email.isNotEmpty() and password.isNotEmpty()) {
                    createUser(
                        email,
                        password,
                        confirmpassword,
                        navController,
                        context
                    )
                } else {
                    Toast.makeText(context, "Los campos estan vacios", Toast.LENGTH_SHORT).show()
                    navController.navigate(route = AppScreens.SignUpScreen.route)
                }
            }) {
                Text(
                    text = "Registrarse"
                )
            }
        }
    }
}


fun createUser(
    email: String,
    password: String,
    confirmaPassword: String,
    navController: NavController,
    context: Context
) {
    val auth = Firebase.auth

    if (password == confirmaPassword) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->

            if (task.isSuccessful) {
                Toast.makeText(context, "Inicio de Sesion Exitoso", Toast.LENGTH_SHORT).show()
                navController.navigate(route = AppScreens.LoginScreen.route)
            } else {
                Toast.makeText(
                    context,
                    "Error al iniciar Sesion:${task.exception}",
                    Toast.LENGTH_SHORT
                ).show()
                navController.navigate(route = AppScreens.SignUpScreen.route)
            }
        }

    } else {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        navController.navigate(route = AppScreens.SignUpScreen.route)
    }


}

@Preview(showBackground = true)
@Composable
fun SignUPreview(){
    SignUpScreen(navController = rememberNavController())
}