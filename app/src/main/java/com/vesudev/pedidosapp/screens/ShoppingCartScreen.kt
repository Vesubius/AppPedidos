package com.vesudev.pedidosapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.cartViewModel.CartViewModel
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.extractFileNameWithoutExtension
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
            val Name = extractFileNameWithoutExtension(item)
            Column {
                Row() {
                    Box {
                        // Mostrar cada imagen en el carrito
                        AsyncImage(
                            model = item, // item es la URL
                            contentDescription = "imagen de $Name",
                            modifier = Modifier
                                .width(200.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )

                        //Eliminar item
                        IconButton(onClick = {
                            cartViewModel.deleteItem(url = item)
                            navController.navigate(route = AppScreens.ShoppingCartScreen.route)
                        }) {
                            Icon(
                                modifier = Modifier
                                    .padding(8.dp)
                                    .background(Color.White)
                                    .height(60.dp),
                                imageVector = Icons.Default.Clear,
                                contentDescription = null,

                                )
                        }
                    }

                    Column(modifier = Modifier.padding(10.dp)) {
                        Text(text = "$Name", fontWeight = FontWeight.Bold)
                        Text(text = "Precio: ${priceDesigner(Name)}", fontWeight = FontWeight.Bold)
                        //Contador de productos
                        Counter()

                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre imágenes

        }
    }
}




@Composable
fun Counter() {
    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {}) {
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Quitar Product")
        }
        Text(text = "0")

        IconButton(onClick = {}) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Agregar Product")
        }
    }
}


sealed class ProductDB(val name: String, val price: String) {
    object Lomo : ProductDB("lomo", "₡2000")
    object Cerdo : ProductDB("Cerdo", "₡1800")
    object Salchichon : ProductDB("Salchichón", "₡1000")
    object Chuleta : ProductDB("chuleta","₡3500")
    object Bisteck : ProductDB("Bisteck","₡2500")

    companion object {
        fun findPriceByName(name: String): String {
            return when (name) {
                Lomo.name -> Lomo.price
                Cerdo.name -> Cerdo.price
                Salchichon.name -> Salchichon.price
                Chuleta.name -> Chuleta.price
                Bisteck.name -> Bisteck.name
                else -> "No se encontró el producto"
            }
        }
    }
}

fun priceDesigner(name: String): String {
    return ProductDB.findPriceByName(name)
}