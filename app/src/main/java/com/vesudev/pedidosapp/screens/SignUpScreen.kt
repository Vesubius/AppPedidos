package com.vesudev.pedidosapp.screens

import android.content.Context
import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.UserProfileChangeRequest
import com.google.firebase.auth.ktx.auth
import com.google.firebase.ktx.Firebase
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.GmailTextField
import com.vesudev.pedidosapp.reusable.PasswordTextField

import com.vesudev.pedidosapp.reusable.UserTextField
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun SignUpScreen(navController: NavController) {
    PedidosAppTheme {

        Scaffold(
            topBar = { SignUpTopBar() },

            content = { innerPadding ->
                Column(modifier = Modifier.padding(innerPadding)) {
                    SignUpContent(navController)
                }
            })


    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SignUpTopBar() {
    TopAppBar(
        title = {
            Text(
                text = "Registrarse",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary,
                modifier = Modifier.fillMaxWidth()
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun SignUpContent(navController: NavController) {
//Variables
    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
    var confirmpassword by remember { mutableStateOf("") }
    var userName by remember { mutableStateOf("") }
    val context = LocalContext.current

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.SpaceEvenly
    ) {

        UserTextField(userName,"Nombre de usuario", onUserChange ={userName = it} )

        GmailTextField(email, onEmailChange = {email = it})

        PasswordTextField(password = password, labeltext = "Contraseña", onPasswordChange = {password = it})
        
        PasswordTextField(password = confirmpassword, labeltext = "Confirmar Contraseña", onPasswordChange = {confirmpassword = it})

        Button(onClick = {
            Register(email, password, confirmpassword, navController, context,userName)
        }) {
            Text(
                text = "Registrarse",fontSize = 20.sp
            )
        }
    }
}


fun Register(
    email: String,
    password: String,
    confirmpassword: String,
    navController: NavController,
    context: Context,
    user: String
) {
    if (email.isNotEmpty() and password.isNotEmpty()) {
        createUser(
            email,
            password,
            confirmpassword,
            navController,
            context,
            user

        )
    } else {
        Toast.makeText(context, "Los campos estan vacios", Toast.LENGTH_SHORT).show()
        navController.navigate(route = AppScreens.SignUpScreen.route)
    }
}


fun createUser(
    email: String,
    password: String,
    confirmaPassword: String,
    navController: NavController,
    context: Context,
    user: String
) {
    val auth = Firebase.auth

    if (password == confirmaPassword) {
        auth.createUserWithEmailAndPassword(email, password).addOnCompleteListener { task ->
            if (task.isSuccessful) {
                // Obtenemos el usuario actual
                val firebaseUser = auth.currentUser
                // Creamos la solicitud de actualización de perfil con el displayName
                val profileUpdates = UserProfileChangeRequest.Builder()
                    .setDisplayName(user)
                    .build()

                // Actualizamos el perfil del usuario
                firebaseUser?.updateProfile(profileUpdates)?.addOnCompleteListener { profileTask ->
                    if (profileTask.isSuccessful) {
                        Toast.makeText(context, "Cuenta Creada", Toast.LENGTH_LONG).show()
                        navController.navigate(route = AppScreens.LoginScreen.route)
                    } else {
                        Toast.makeText(
                            context,
                            "Error al actualizar perfil: ${profileTask.exception?.message}",
                            Toast.LENGTH_LONG
                        ).show()
                    }
                }
            } else {
                Toast.makeText(
                    context,
                    "Error al Crear Cuenta: ${task.exception?.message}",
                    Toast.LENGTH_LONG
                ).show()
                navController.navigate(route = AppScreens.SignUpScreen.route)
            }
        }
    } else {
        Toast.makeText(context, "Las contraseñas no coinciden", Toast.LENGTH_SHORT).show()
        navController.navigate(route = AppScreens.SignUpScreen.route)
    }
}
