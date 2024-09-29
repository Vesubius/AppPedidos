package com.vesudev.pedidosapp.screens

import android.util.Log
import android.widget.Space
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController

import com.vesudev.pedidosapp.R
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage

@Composable
fun BasicAppScreen(navController: NavController) {
    PedidosAppTheme {
        // Usamos mutableStateListOf para que la lista sea observable y se recomponga la UI
        val imageList = remember { mutableStateListOf<String>() }

        // Cargar las fotos de Firebase
        LaunchedEffect(Unit) {
            getFirebasePhotos(imageList)
        }
        Column(modifier = Modifier.fillMaxSize()) {

            Text("Produtos", fontSize = 20.sp)
            // Mostrar las imágenes en una lista
            LazyRow(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically
            ) {

                items(imageList) { imageUrl ->

                    Box(
                        modifier = Modifier
                            .background(Color.Black),
                        contentAlignment = Alignment.Center
                    ) {
                        Box(
                            modifier = Modifier
                                .width(200.dp)
                                .padding(2.dp)
                                .background(color = MaterialTheme.colorScheme.background),
                            contentAlignment = Alignment.Center
                        ) {
                            Column(horizontalAlignment = Alignment.CenterHorizontally) {

                                // Usamos AsyncImage para cargar la imagen desde la URL
                                AsyncImage(
                                    modifier = Modifier
                                        .padding(5.dp)
                                        .width(200.dp)
                                        .height(200.dp),
                                    model = imageUrl,
                                    contentDescription = "Imagen de Firebase",
                                    contentScale = ContentScale.Crop,
                                    placeholder = painterResource(id = R.drawable.logo)
                                )
                                Button(onClick = {}) { Text("Ordenar") }
                            }

                        }

                    }
                }
            }
        }
    }

}

// Función para obtener fotos de Firebase y añadirlas a la lista
fun getFirebasePhotos(imageList: MutableList<String>) {
    val storage = Firebase.storage
    val storageRef =
        storage.reference.child("Productos")  // Solo usa el nombre de la carpeta que esta en la galeria de firebase

    storageRef.listAll().addOnSuccessListener { result ->
        for (fileRef in result.items) {
            Log.d("FirebaseStorage", "paso el for")
            fileRef.downloadUrl.addOnSuccessListener { uri ->
                imageList.add(uri.toString())  // Añadir la URL a la lista

                Log.d("FirebaseStorage", "Imagen añadida: $uri")
            }
        }
    }.addOnFailureListener { exception ->

        Log.e("FirebaseStorage", "Error al listar imágenes", exception)
    }
}

@Preview(showBackground = true)
@Composable
fun PreviewBasicAppScreen() {
    BasicAppScreen(navController = rememberNavController())
}
