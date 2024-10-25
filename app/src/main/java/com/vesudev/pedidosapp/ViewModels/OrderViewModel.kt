package com.vesudev.pedidosapp.ViewModels
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.map

data class Order(
    val NombreCliente: String,
    val PrecioPedido: String
)

class OrderViewModel : ViewModel() {
    // Lista mutable de pedidos
    private val _orders = MutableLiveData<MutableList<Order>>(mutableListOf())

    // Convertir MutableLiveData<MutableList<Order>> a LiveData<List<Order>>
    val orders: LiveData<List<Order>> = _orders.map { it.toList() } // Convertimos a lista inmutable

    // Función para agregar un nuevo pedido
    fun addOrder(order: Order) {
        _orders.value?.add(order)
        // Notificamos el cambio a la lista
        _orders.value = _orders.value
    }
}