package com.praktikum.lumera.navigation

sealed class Screen {

    data object Splash : Screen()

    data object Onboarding : Screen()

    data object Login : Screen()

    data object Register : Screen()

    data object Home : Screen()

    data object Cart : Screen()

    data object Payment : Screen()

    data object Receipt : Screen()

    data object Admin : Screen()

    data class Detail(
        val itemName: String,
        val price: Int
    ) : Screen()
}