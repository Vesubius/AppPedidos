package com.vesudev.pedidosapp.cartViewModel

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf

import androidx.compose.runtime.remember
import androidx.lifecycle.ViewModel



class CartViewModel : ViewModel() {

    val cartItems = mutableListOf<String>()


    fun addItem(url: String) {
        if (cartItems.contains(url)) {

        }else {
            cartItems.add(url)
        }
    }

    fun deleteItem(url: String) {
        cartItems.remove(url)
    }
}