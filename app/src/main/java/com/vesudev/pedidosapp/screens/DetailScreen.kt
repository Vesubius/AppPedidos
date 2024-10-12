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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.navigation.AppScreens


@Composable
fun DetailScreen(
    navController: NavController,
    fileName: String,
    imageUrl: String,

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
        ) { DetailContent(imageUrl, fileName) }
    }
}

@Composable
fun DetailContent(imageUrl: String, fileName: String) {
    // Mostrar la imagen
    AsyncImage(
        model = imageUrl,
        contentDescription = fileName,
        modifier = Modifier
            .height(200.dp)
            .fillMaxWidth(),
        contentScale = ContentScale.Crop
    )

    Spacer(modifier = Modifier.height(16.dp))

    // Detalles adicionales
    Text(
        text = "Detalles del producto: $fileName",
        modifier = Modifier.padding(8.dp)
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun DetailTopApBar(fileName: String, navController: NavController) {
    TopAppBar(
        title = { Text(text = fileName) },
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppScreens.MainAppScreen.route) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}