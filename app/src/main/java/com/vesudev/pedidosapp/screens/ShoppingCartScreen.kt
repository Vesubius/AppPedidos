package com.vesudev.pedidosapp.screens


import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun ShoppingCartScreen(navController: NavController) {

    PedidosAppTheme {
        Scaffold(
            topBar = { ShoppingTopBar() },
            content = { innerPaddin ->
                Column(modifier = Modifier.padding(innerPaddin)) {
                    ShoppingContent(navController)
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingTopBar() {
    TopAppBar(
        title = { Text("Su Compra") },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary)
    )
}

@Composable
fun ShoppingContent(navController: NavController) {

    LazyColumn() {
        item {
            Column(modifier = Modifier.fillMaxSize()) {
                AsyncImage(modifier = Modifier.height(200.dp),
                    contentScale = ContentScale.Crop,
                    model = "https://firebasestorage.googleapis.com/v0/b/pedidosapp-b7e90.appspot.com/o/Carnes%2Fchuleta.jpeg?alt=media&token=1654bdfd-5666-4308-88ec-2f244fab2428",
                    contentDescription = "Producto"
                )
                Text("Nombre")
                Text("Precio")
            }
        }
    }

}


@Preview(showBackground = true)
@Composable
private fun ShoppingScreenPreview() {
    val navController = rememberNavController()
    ShoppingCartScreen(navController)
}