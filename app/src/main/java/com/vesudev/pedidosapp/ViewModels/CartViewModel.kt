package com.vesudev.pedidosapp.ViewModels

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import com.vesudev.pedidosapp.reusable.extractFileNameWithoutExtension
import com.vesudev.pedidosapp.screens.priceDesigner


data class CartItem(
    val url: String,
    var cantidad: Int = 1
)

class CartViewModel : ViewModel() {
    private val _cartItems = mutableStateListOf<CartItem>()
    val cartItems: List<CartItem> = _cartItems

    var totalProductCount by mutableStateOf(0)


    fun addItem(url: String) {
        val existingItem = _cartItems.find { it.url == url }
        if (existingItem != null) {
            existingItem.cantidad += 1
        } else {
            _cartItems.add(CartItem(url = url))
        }
        updateTotalProductCount()
    }

    fun deleteItem(url: String) {
        _cartItems.removeAll { it.url == url }
        updateTotalProductCount()
    }

    private fun updateTotalProductCount() {
        totalProductCount = _cartItems.sumOf { it.cantidad }
    }

    fun productCounterIncrement(item: CartItem) {
        item.cantidad += 1
        updateTotalProductCount()
    }

    fun productCounterDecrement(item: CartItem) {
        if (item.cantidad > 1) {
            item.cantidad -= 1
        }
        updateTotalProductCount()
    }

    fun getCartItemsValue():Int {
        return _cartItems.size
    }

    fun totalPriceOfOrder(): Int {
        var total = 0
        for (item in _cartItems) {
            val price = priceDesigner(extractFileNameWithoutExtension(item.url))
            total += price * item.cantidad
        }
        return total
    }


}