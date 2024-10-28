package com.vesudev.pedidosapp.AdminDB



data class AdminsDB(val gmail:String){
    object User{
        val Jeffry = "jeffryperezduarte@gmail.com"
        val VesuDev = "vesudev18@gmail.com"

        val UserList = mutableListOf(Jeffry,VesuDev)
    }

}
