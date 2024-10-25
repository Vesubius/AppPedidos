package com.vesudev.pedidosapp.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.DateRange
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.vesudev.pedidosapp.ViewModels.Order
import com.vesudev.pedidosapp.navigation.AppScreens
import com.vesudev.pedidosapp.ui.theme.PedidosAppTheme

@Composable
fun OrdersScreen(
    navController: NavController,
) {


    PedidosAppTheme {
        Scaffold(
            topBar = { OrdersScreenTopBar(navController) },
            content = { innerPadding ->
                OrdersScreenContent(
                    innerPadding,
                    navController = navController

                )
            }
        )
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun OrdersScreenTopBar(navController: NavController) {
    TopAppBar(
        title = {
            Text(
                modifier = Modifier.fillMaxWidth(),
                text = "Centro de Carnes San Isidro (ADMIN)",
                textAlign = TextAlign.Center
            )
        },
        colors = TopAppBarDefaults.topAppBarColors(MaterialTheme.colorScheme.primary),
        navigationIcon = {
            IconButton(onClick = { navController.navigate(route = AppScreens.ProfileScreen.route) }) {
                Icon(
                    Icons.Default.Person,
                    contentDescription = "Perfil"
                )
            }
        }
    )
}

@Composable
fun OrdersScreenContent(
    padding: PaddingValues,
    navController: NavController,

) {
    val orders = listOf(
        Order("Jeffry Perez Duarte", "8.500 kg"),
        Order("Jeffry Perez Duarte", "6.300 kg"),
        Order("Jeffry Perez Duarte", "9.200 kg"),
        Order("Jeffry Perez Duarte", "20.400 kg")
    )
    Row(modifier = Modifier.fillMaxSize()) {

        // Sidebar con los botones (Historia, Ajustes, etc.)
        Column(
            modifier = Modifier
                .width(80.dp)
                .fillMaxHeight()
                .background(Color.LightGray),
            verticalArrangement = Arrangement.Top
        ) {
            Spacer(modifier = Modifier.padding(padding))
            IconButton(onClick = { /* Acción para ver historial */ }) {
                Icon(Icons.Default.DateRange, contentDescription = "Historial")
            }
            IconButton(onClick = { /* Acción para abrir ajustes */ }) {
                Icon(Icons.Default.Settings, contentDescription = "Ajustes")
            }
        }

        // Lista de pedidos
        Column(
            modifier = Modifier
                .fillMaxHeight()
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Spacer(modifier = Modifier.padding(padding))
            Text(
                text = "Pedidos",
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold,
                color = MaterialTheme.colorScheme.primary,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(8.dp)
            )

            LazyColumn(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(top = 8.dp),
                verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
                items(orders) { order ->
                    OrderItem(order)
                }
            }
        }
    }
}

@Composable
fun OrderItem(order: Order) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp)
        ) {
            Text(
                text = order.PrecioPedido,
                fontSize = 18.sp,
                fontWeight = FontWeight.Bold,
                modifier = Modifier.padding(bottom = 8.dp)
            )
            Text(
                text = order.NombreCliente,
                fontSize = 16.sp,
                modifier = Modifier.padding(bottom = 8.dp)
            )

            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                Button(onClick = { /* Acción para detalles */ }) {
                    Text("Detalles")
                }
                Button(
                    onClick = {  },
                    colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.secondary)
                ) {
                    Text("Archivar")
                }
            }
        }
    }
}

