package com.vesudev.pedidosapp.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel


data class CartItem(
    val url: String,
    var cantidad: Int = 1
)

class CartViewModel : ViewModel() {
    val cartItems = mutableStateListOf<CartItem>()

    fun addItem(url: String) {
        val existingItem = cartItems.find { it.url == url }
        if (existingItem != null) {
            existingItem.cantidad += 1
        } else {
            cartItems.add(CartItem(url = url))
        }
    }

    fun deleteItem(url: String) {
        cartItems.removeAll { it.url == url }
    }

    fun productCounterIncrement(item: CartItem) {
        item.cantidad += 1
    }

    fun productCounterDecrement(item: CartItem) {
        if (item.cantidad > 1) {
            item.cantidad -= 1
        }
    }

    fun getCartItemsValue():Int {
        return cartItems.size
    }

}