package com.vesudev.pedidosapp.cartViewModel

import androidx.lifecycle.ViewModel

class CartViewModel : ViewModel() {

    val cartItems = mutableListOf<String>()


    fun addItem(url: String) {
        cartItems.add(url)
    }

    fun deleteItem(url: String){
        cartItems.remove(url)
    }
}