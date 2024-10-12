package com.vesudev.pedidosapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.outlined.Clear
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.cartViewModel.CartViewModel
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun ShoppingCartScreen(navController: NavController, cartViewModel: CartViewModel) {

    PedidosAppTheme {
        Scaffold(
            topBar = { ShoppingTopBar(navController) },
            content = { innerPaddin ->
                Column(modifier = Modifier.padding(innerPaddin)) {
                    ShoppingContent(navController, cartViewModel)
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingTopBar(navController: NavController) {
    TopAppBar(
        title = { Text("Su Compra") },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppScreens.MainAppScreen.route) }) {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }
    )
}

@Composable
fun ShoppingContent(navController: NavController, cartViewModel: CartViewModel) {

    LazyColumn {
        items(cartViewModel.cartItems) { item ->
            Column {
                Box {

                    // Mostrar cada imagen en el carrito
                    AsyncImage(
                        model = item, // item es la URL
                        contentDescription = null,
                        modifier = Modifier
                            .height(200.dp)
                            .fillMaxWidth()
                            .padding(8.dp),
                        contentScale = ContentScale.Crop
                    )

                    //Eliminar item
                    IconButton(onClick = {
                        cartViewModel.deleteItem(url = item)
                        navController.navigate(route = AppScreens.ShoppingCartScreen.route)
                    }) {
                        Icon(
                            modifier = Modifier.padding(8.dp)
                                .background(Color.White)
                                .height(60.dp),
                            imageVector = Icons.Default.Clear,
                            contentDescription = null,

                        )
                    }
                }

            }
            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre imágenes

        }
    }
}




