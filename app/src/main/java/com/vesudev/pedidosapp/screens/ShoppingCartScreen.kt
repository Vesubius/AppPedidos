package com.vesudev.pedidosapp.screens


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
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
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCompositionContext
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import coil.compose.AsyncImage
import com.vesudev.pedidosapp.ViewModels.CartItem
import com.vesudev.pedidosapp.ViewModels.CartViewModel
import com.vesudev.pedidosapp.ViewModels.OrderViewModel

import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.reusable.extractFileNameWithoutExtension
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun ShoppingCartScreen(
    navController: NavController,
    cartViewModel: CartViewModel,
    orderViewModel: OrderViewModel
) {
    PedidosAppTheme {
        Scaffold(
            topBar = { ShoppingTopBar(navController) },

            content = { innerPaddin ->
                Column(modifier = Modifier.padding(innerPaddin)) {
                    ShoppingContent(navController, cartViewModel, orderViewModel)
                }
            })
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun ShoppingTopBar(navController: NavController) {
    TopAppBar(

        title = {
            Text(
                text = "Su Compra",
                color = MaterialTheme.colorScheme.onPrimary
            )
        },
        colors = topAppBarColors(MaterialTheme.colorScheme.primary),

        navigationIcon = {
            IconButton(onClick = {
                navController.navigate(route = AppScreens.MainAppScreen.route) {
                    popUpTo(AppScreens.MainAppScreen.route) { inclusive = true }
                }
            }) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Back",
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    )
}

@Composable
fun ShoppingContent(
    navController: NavController,
    cartViewModel: CartViewModel,
    orderViewModel: OrderViewModel,
) {

    LazyColumn {
        items(cartViewModel.cartItems) { cartItem ->
            val productName = extractFileNameWithoutExtension(cartItem.url)
            Column {
                Row {
                    Box {
                        // Mostrar cada imagen en el carrito
                        AsyncImage(
                            model = cartItem.url,
                            contentDescription = "imagen de $productName",
                            modifier = Modifier
                                .width(200.dp)
                                .padding(8.dp),
                            contentScale = ContentScale.Crop
                        )

                        // Eliminar item
                        IconButton(onClick = {
                            cartViewModel.deleteItem(url = cartItem.url)
                            navController.navigate(route = AppScreens.ShoppingCartScreen.route) {
                                popUpTo(AppScreens.ShoppingCartScreen.route) {
                                    inclusive = true
                                }
                            }
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
                        Text(text = "$productName", fontWeight = FontWeight.Bold)
                        Text(
                            text = "Precio ₡${priceDesigner(productName).toString()}",
                            fontWeight = FontWeight.Bold
                        )
                        // Contador de productos
                        Counter(cartViewModel, cartItem, navController) // Pasamos el cartItem
                    }
                }
            }

            Spacer(modifier = Modifier.height(8.dp)) // Espaciado entre imágenes
        }
        item {
            Spacer(modifier = Modifier.padding(top = 40.dp))
            Column(
                modifier = Modifier.fillMaxWidth(),
                verticalArrangement = Arrangement.Bottom,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                val cartItemsSize = cartViewModel.getCartItemsValue()
                // Verificamos que exista algo en el carrito antes de mostrar el boton de hacer pedido
                if (cartItemsSize != 0) {
                    Button(onClick = { /* Hacer pedido */ }) {
                        Text(
                            text = "Hacer Pedido",
                            fontSize = 18.sp,
                            textAlign = TextAlign.Center
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun Counter(cartViewModel: CartViewModel, cartItem: CartItem, navController: NavController) {

    Row(verticalAlignment = Alignment.CenterVertically) {
        IconButton(onClick = {
            cartViewModel.productCounterDecrement(cartItem)
            navController.navigate(route = AppScreens.ShoppingCartScreen.route) {
                popUpTo(AppScreens.ShoppingCartScreen.route) {
                    inclusive = true
                }
            }
        }) {
            Icon(Icons.Filled.KeyboardArrowDown, contentDescription = "Quitar Product")
        }
        Text(text = cartItem.cantidad.toString()) // Usamos la cantidad del cartItem

        IconButton(onClick = {
            cartViewModel.productCounterIncrement(cartItem)
            navController.navigate(route = AppScreens.ShoppingCartScreen.route) {
                popUpTo(AppScreens.ShoppingCartScreen.route) {
                    inclusive = true
                }
            }
        }) {
            Icon(Icons.Default.KeyboardArrowUp, contentDescription = "Agregar Product")
        }
    }
}


sealed class ProductDB(val name: String, val price: Int) {
    //Carnes
    object Lomo : ProductDB("lomo", 2000)
    object Chuleta : ProductDB("chuleta", 3500)
    object Bisteck : ProductDB("bisteck", 2500)
    object Posta : ProductDB("posta", 3400)
    object Rebay : ProductDB("rebay", 5200)

    //Embutidos
    object Peperoni : ProductDB("Peperoni", 2400)
    object Chorizo : ProductDB("Chorizo", 3100)
    object PeperoniItaliano : ProductDB("Peperoni Italiano", 3400)
    object Salchicha : ProductDB("Salchicha", 2200)
    object SalchichaParrillera : ProductDB("Salchicha Parrillera", 3600)
    object Salchichon : ProductDB("Salchichon", 1000)

    companion object {
        fun findPriceByName(name: String): Int {
            return when (name) {
                //Carnes
                Lomo.name -> Lomo.price
                Chuleta.name -> Chuleta.price
                Bisteck.name -> Bisteck.price
                Posta.name -> Posta.price
                Rebay.name -> Rebay.price

                //Embutidos
                Salchichon.name -> Salchichon.price
                Peperoni.name -> Peperoni.price
                Chorizo.name -> Chorizo.price
                PeperoniItaliano.name -> PeperoniItaliano.price
                Salchicha.name -> Salchicha.price
                SalchichaParrillera.name -> SalchichaParrillera.price
                else -> -1
            }
        }

    }
}

fun priceDesigner(name: String): Int {
    return ProductDB.findPriceByName(name)
}