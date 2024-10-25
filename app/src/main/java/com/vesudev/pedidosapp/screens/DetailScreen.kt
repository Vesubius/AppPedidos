package com.vesudev.pedidosapp.screens


import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.ViewModels.CartViewModel
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme


@Composable
fun DetailScreen(
    navController: NavController,
    fileName: String,
    imageUrl: String,
    cartViewModel: CartViewModel,

    ) {
    Scaffold(
        topBar = { DetailTopApBar(fileName, navController) }

    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Top
        ) { DetailContent(imageUrl, fileName, cartViewModel) }
    }
}

@Composable
fun DetailContent(imageUrl: String, fileName: String, cartViewModel: CartViewModel) {


    // Mostrar la imagen
    Product(imageUrl, fileName)

    Spacer(modifier = Modifier.height(16.dp))

    // Detalles adicionales
    Text(
        text = "Detalles del producto: $fileName",
        modifier = Modifier.padding(8.dp)
    )

    //Boton agregar al carrito
    Button(onClick = { cartViewModel.addItem(imageUrl) }) {
        Text(text = "Agregar al carrito")
    }
}


@Composable
fun Product(imageUrl: String, fileName: String) {
    AsyncImage(
        model = imageUrl,
        contentDescription = fileName,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopApBar(fileName: String, navController: NavController) {
    PedidosAppTheme {
        TopAppBar(
            title = { Text(text = fileName) },
            colors = topAppBarColors(MaterialTheme.colorScheme.primary),
            navigationIcon = {
                IconButton(onClick = { navController.navigate(route = AppScreens.MainAppScreen.route) }) {
                    Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
                }
            }
        )
    }
}