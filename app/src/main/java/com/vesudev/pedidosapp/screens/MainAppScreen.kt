package com.vesudev.pedidosapp.screens


import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size

import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale


import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun MainAppScreen(navController: NavController) {
    PedidosAppTheme {

        val urlsCarnes = remember { mutableStateListOf("") }
        val urlsEmbutidos = remember { mutableStateListOf("") }

        LaunchedEffect(urlsEmbutidos, urlsCarnes) {
            //Obtener Imagenes de la carpeta Embutidos en Firebase
            getImagesOnFirebase("Embutidos", urlsEmbutidos)

            //Obtener Imagenes de la carpeta Carnes en Firebase
            getImagesOnFirebase("Carnes", urlsCarnes)
        }
        Column(
            modifier = Modifier.fillMaxSize(),
            verticalArrangement = Arrangement.SpaceEvenly,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Spacer(modifier = Modifier.padding(15.dp))
            TopBar(navController)

            Column(
                modifier = Modifier.fillMaxSize(),
                verticalArrangement = Arrangement.SpaceEvenly
            ) {

                //Seccion Carnes
                Box {

                    Column {
                        //Titulo de Seccion Carnes
                        Text(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .fillMaxWidth(),
                            text = "Seccion Carnes",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (urlsCarnes.isNotEmpty()) {
                            LazyRow(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {

                                //Lazy Row de Imagenes de Carnes
                                items(urlsCarnes.size) { image ->
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(160.dp)
                                            .padding(5.dp),
                                        model = urlsCarnes[image],
                                        contentDescription = "Foto de Carnes",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        } else {
                            Text(text = "Cargando imagen...")
                        }
                    }
                }


                //Seccion Embutidos

                Box {

                    Column {
                        //Titulo de Seccion Embutidos
                        Text(
                            modifier = Modifier
                                .background(MaterialTheme.colorScheme.primary)
                                .fillMaxWidth(),
                            text = "Seccion Embutidos",
                            fontSize = 18.sp,
                            fontWeight = FontWeight.Bold
                        )
                        if (urlsEmbutidos.isNotEmpty()) {

                            LazyRow(
                                modifier = Modifier
                                    .background(MaterialTheme.colorScheme.primary)
                            ) {
                                //Lazy Row de Imagenes de Carnes
                                items(urlsEmbutidos.size) { image ->
                                    AsyncImage(
                                        modifier = Modifier
                                            .height(160.dp)
                                            .padding(5.dp),
                                        model = urlsEmbutidos[image],
                                        contentDescription = "Foto de Carnes",
                                        contentScale = ContentScale.Crop
                                    )
                                }
                            }
                        }


                    }
                }

            }
        }

    }

}



fun getImagesOnFirebase(folderName: String, imageURLlist: SnapshotStateList<String>) {
    //inicializamos Firebase
    val storage = Firebase.storage
    //obtenemos la referencia a la galeria de Firebase
    val storageRef = storage.reference
    //obtenemos la referencia a la carpeta de imagenes de Firebase
    val imagenes = storageRef.child(folderName)



    imagenes.listAll().addOnSuccessListener { listResult ->
        // Aquí obtienes todas las referencias a los archivos en la carpeta

        for (list in listResult.items) {
            // Para cada archivo en la lista, puedes obtener su URL de descarga

            list.downloadUrl.addOnSuccessListener { uri ->
                //Agregar la URL de la imagen a la lista de URLs solo si no está duplicada
                val url = uri.toString()
                if (url.isNotBlank() && !imageURLlist.contains(url) && url.isNotEmpty()) {
                    imageURLlist.add(url)
                }

            }.addOnFailureListener { exception ->
                // esto se ejecuta si hay cualquier error al obtener la URL
                Log.e("FirebaseStorage", "Failed to get download URL: ${exception.message}")
            }

        }

    }.addOnFailureListener { exception ->
        // Maneja cualquier error al listar los archivos en la carpeta
        Log.e("FirebaseStorage", "Failed to list files: ${exception.message}")
    }

}


@Composable
fun TopBar(navController: NavController) {
    //Barra de Navegacion
    Row(
        modifier = Modifier
            .background(MaterialTheme.colorScheme.primary)
            .height(40.dp)
            .fillMaxWidth(),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Icon(
            imageVector = Icons.Default.Person,
            contentDescription = "Perfil de Usuario",
            modifier = Modifier
                .size(40.dp)
                .padding(end = 5.dp)
        )

        Text(text = "Centro de Carnes San Isidro", fontSize = 20.sp)

        Image(
            modifier = Modifier
                .clickable {
                    navController.navigate(route = AppScreens.ShoppingCartScreen.route)
                }
                .size(40.dp)
                .padding(end = 5.dp),

            painter = painterResource(id = R.drawable.shoppingcart),
            contentDescription = "Carrito de compras"
        )
    }
}




