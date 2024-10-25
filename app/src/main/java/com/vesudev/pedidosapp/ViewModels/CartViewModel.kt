package com.vesudev.pedidosapp.ViewModels

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