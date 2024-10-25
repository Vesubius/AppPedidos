package com.vesudev.pedidosapp.screens


import android.annotation.SuppressLint
import android.net.Uri
import android.util.Log
import androidx.compose.foundation.ScrollState
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.snapshots.SnapshotStateList

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale


import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.ktx.storage
import com.vesudev.pedidosapp.cartViewModel.CartViewModel
import com.vesudev.pedidosapp.navigation.AppScreens

import com.vesudev.pedidosapp.reusable.extractFileNameWithoutExtension
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun MainAppScreen(
    navController: NavController,
    urlsEmbutidos: SnapshotStateList<String>,
    urlsCarnes: SnapshotStateList<String>,
    cartViewModel: CartViewModel
) {

    PedidosAppTheme {


        //Recuperar imagenes
        LaunchedEffect(urlsEmbutidos, urlsCarnes) {
            //Limpiar la lista de URLs antes de agregar nuevas
            urlsEmbutidos.clear()
            //Obtener Imagenes de la carpeta Embutidos en Firebase
            getUrlListOnFirebase("Embutidos", urlsEmbutidos)

            //Limpiar la lista de URLs antes de agregar nuevas
            urlsCarnes.clear()
            //Obtener Imagenes de la carpeta Carnes en Firebase
            getUrlListOnFirebase("Carnes", urlsCarnes)
        }


        Scaffold(

            topBar = { MainAppScreenTopBar(navController, cartViewModel) },

            content = { innerPadding ->
                MainAppContent(
                    modifier = Modifier.padding(innerPadding),
                    urlsCarnes, urlsEmbutidos, navController,cartViewModel
                )
            })

    }
}


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainAppScreenTopBar(navController: NavController, cartViewModel: CartViewModel) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Centro de Carnes San Isidro",
                textAlign = TextAlign.Center,
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppScreens.ProfileScreen.route) }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Perfil",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        },

        actions = {
            IconButton(onClick = { navController.navigate(route = AppScreens.ShoppingCartScreen.route) }) {
                Icon(
                    Icons.Default.ShoppingCart,
                    contentDescription = "Carrito de compras",
                    tint = MaterialTheme.colorScheme.onPrimary,

                )
            }
        }
    )
}

@Composable
fun MainAppContent(
    modifier: Modifier,
    urlcarnes: SnapshotStateList<String>,
    urlEmbutidos: SnapshotStateList<String>,
    navController: NavController,
    cartViewModel: CartViewModel
) {


    // variable de estado de scroll de la lista de carnes
    val scrollCarnes = rememberScrollState()
    val scrollEmbutidos = rememberScrollState()


    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.SpaceEvenly,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        //Seccion de carnes
        MeatSection(
            scrollCarnes = scrollCarnes,
            urlcarnes = urlcarnes,
            navController = navController,
            cartViewModel = cartViewModel

        )

        //Seccion Embutidos
        sausagesSection(scrollEmbutidos = scrollEmbutidos,
            urlEmbutidos = urlEmbutidos,
            navController = navController,
            cartViewModel = cartViewModel)

    }
}


@Composable
fun MeatSection(
    scrollCarnes: ScrollState,
    urlcarnes: SnapshotStateList<String>,
    navController: NavController,
    cartViewModel: CartViewModel
) {

    Box {
        Column {
            //Titulo de la seccion
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Carnes",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .horizontalScroll(state = scrollCarnes)
            ) {

                Row() {

                    urlcarnes.forEach() { uri ->
                        val name = extractFileNameWithoutExtension(uri)
                        val encodedUri = Uri.encode(uri)
                        Column(horizontalAlignment = Alignment.CenterHorizontally) {
                            AsyncImage(
                                modifier = Modifier
                                    .clickable { navController.navigate("detail/${name}/${encodedUri}") }
                                    .padding(5.dp)
                                    .height(150.dp),
                                model = uri,
                                contentDescription = "",
                                contentScale = ContentScale.Crop
                            )

                            Button(onClick = {cartViewModel.addItem(uri)}) { Text(text = "Agregar $name") }
                        }
                    }

                }

            }
        }
    }
}


@Composable
fun sausagesSection(
    scrollEmbutidos: ScrollState,
    urlEmbutidos: SnapshotStateList<String>,
    navController: NavController,
    cartViewModel: CartViewModel

) {
    //Seccion de Embutidos
    Box {
        Column {
            //Titulo de la seccion
            Text(
                modifier = Modifier
                    .fillMaxWidth(),
                text = "Embutidos",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
            )
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .border(width = 1.dp, color = Color.Black)
                    .horizontalScroll(state = scrollEmbutidos)
            ) {
                urlEmbutidos.forEach() { uri ->
                    val name = extractFileNameWithoutExtension(uri)
                    val encodedUri = Uri.encode(uri)
                    Column(horizontalAlignment = Alignment.CenterHorizontally) {
                        AsyncImage(
                            modifier = Modifier
                                .clickable { navController.navigate("detail/${name}/${encodedUri}") }
                                .padding(5.dp)
                                .height(150.dp),
                            model = uri,
                            contentDescription = "",
                            contentScale = ContentScale.Crop
                        )

                        Button(onClick = {cartViewModel.addItem(uri)}) { Text(text = "Agregar $name") }
                    }
                }

            }
        }
    }
}


fun getUrlListOnFirebase(folderName: String, imageURLlist: SnapshotStateList<String>) {


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
