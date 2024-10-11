package com.vesudev.pedidosapp.reusable

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens

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


@Composable
fun watermark() {
    Text(
        modifier = Modifier
            .fillMaxWidth()
            .padding(end = 10.dp),
        textAlign = TextAlign.End,
        text = "By VesuDev"
    )

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
fun PasswordTextField(password: String, onPasswordChange: (String) -> Unit) {
    var isPasswordVisible by remember { mutableStateOf(false) }

    //Campo Contraseña
    Box {
        Column {
            Text(text = "Contraseña", fontWeight = FontWeight.Normal, fontSize = 20.sp)
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














