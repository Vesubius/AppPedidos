package com.vesudev.pedidosapp.ViewModels

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import com.vesudev.pedidosapp.reusable.extractFileNameWithoutExtension
import com.vesudev.pedidosapp.screens.priceDesigner


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

    fun totalPriceOfOrder(): Int {
        var total = 0
        for (item in cartItems) {
            val price = priceDesigner(extractFileNameWithoutExtension(item.url))
            total += price * item.cantidad
        }
        return total
    }


}