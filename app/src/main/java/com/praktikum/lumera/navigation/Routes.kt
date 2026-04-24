package com.praktikum.lumera.navigation

sealed class Screen {
    object Login : Screen()
    object Register : Screen()
    object Home : Screen()
    object Cart : Screen()
    object Payment : Screen()
    object Receipt : Screen()

    data class Detail(
        val itemName: String,
        val price: Int
    ) : Screen()
}