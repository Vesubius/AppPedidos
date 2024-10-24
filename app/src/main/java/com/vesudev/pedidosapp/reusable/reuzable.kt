package com.vesudev.pedidosapp.reusable

import android.content.Intent
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens

//By VesuDev Text(HomeScreen)
@Composable
fun OpenFacebookButton() {
    val context = LocalContext.current

    // URL de fallback si la app de Facebook no está instalada
    val facebookPageUrl = "https://www.facebook.com/100085654833004"
    // URL personalizada para la app de Facebook
    val facebookAppUri = "fb://page/100085654833004"

    // Botón para abrir la página de Facebook
    TextButton(onClick = {
        // Intent para abrir la aplicación de Facebook
        val intent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookAppUri))
        // Verificar si hay alguna actividad que pueda manejar el intent
        if (intent.resolveActivity(context.packageManager) != null) {
            context.startActivity(intent) // Abre la app de Facebook
        } else {
            // Si no está instalada, abre el navegador
            val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse(facebookPageUrl))
            context.startActivity(webIntent)
        }
    }) {
        Text(
            modifier = Modifier
                .fillMaxWidth()
                .padding(end = 10.dp),
            textAlign = TextAlign.End,
            text = "By VesuDev"
        )
    }
}


@Composable
fun TestButton(navController: NavController, text: String) {
    //boton de prueba
    Button(onClick = { navController.navigate(route = AppScreens.MainAppScreen.route) }) {
        Text(
            text
        )
    }
}

@Composable
fun Logo() {
    Image(
        modifier = Modifier
            .height(150.dp)
            .clip(CircleShape),
        painter = painterResource(id = R.drawable.logo),
        contentDescription = "Logo",
    )
}

fun signOut() {
    // Obtén la instancia de FirebaseAuth
    val auth = FirebaseAuth.getInstance()

    // Cierra la sesión
    auth.signOut()

}

@Composable
fun AppTittle() {
    //Titulo
    Text(
        text = "Centro de Carnes San Isidro",
        fontWeight = FontWeight.Bold,
        fontSize = 25.sp
    )
}

@Composable
fun GmailTextField(email: String, onEmailChange: (String) -> Unit) {
    //Campo correo
    Box {
        Column {
            Text(
                text = "Correo Electronico",
                fontWeight = FontWeight.Normal,
                fontSize = 20.sp
            )

            OutlinedTextField(
                modifier = Modifier.padding(top = 10.dp),
                value = email,
                onValueChange = { email -> onEmailChange(email) },
                placeholder = { Text(text = "Correo...") },
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Email
                ),
                singleLine = true
            )
        }
    }
}


@Composable
fun PasswordTextField(password: String,labeltext:String, onPasswordChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    //Campo Contraseña
    Box {
        Column {
            Text(text = labeltext, fontWeight = FontWeight.Normal, fontSize = 20.sp)
            Row() {
                OutlinedTextField(
                    modifier = Modifier.padding(top = 10.dp),
                    value = password,
                    onValueChange = { password -> onPasswordChange(password) },
                    placeholder = { Text(text = "Contraseña...") },
                    keyboardOptions = KeyboardOptions.Default.copy(
                        keyboardType = KeyboardType.Password
                    ),
                    singleLine = true,

                    visualTransformation = if (isPasswordVisible) VisualTransformation.None else PasswordVisualTransformation(),
                    trailingIcon = {
                        IconButton(onClick = { isPasswordVisible = !isPasswordVisible }) {

                            Image(
                                painter = if (isPasswordVisible) painterResource(id = R.drawable.visible) else painterResource(
                                    id = R.drawable.invisible
                                ),
                                contentDescription = "Mostrar/Ocultar Contraseña"
                            )

                        }
                    }
                )

            }

        }
    }

}


fun extractFileNameWithoutExtension(url: String): String {
    // Primero, decodificamos la URL
    val decodedUrl = java.net.URLDecoder.decode(url, "UTF-8")
    // Buscamos el inicio del nombre del archivo después de la última '/'
    val startIndex = decodedUrl.lastIndexOf("/") + 1
    // Buscamos el final del nombre antes de los parámetros '?'
    val endIndex = decodedUrl.indexOf("?")

    // Si ambos índices son válidos, extraemos el nombre del archivo
    if (startIndex != -1 && endIndex != -1) {
        val fileName = decodedUrl.substring(startIndex, endIndex)
        // Quitamos la extensión del archivo (todo lo que sigue al último '.')
        return fileName.substringBeforeLast(".")
    } else {
        return "Nombre desconocido"
    }
}











