package com.praktikum.lumera.navigation

import androidx.compose.runtime.*
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.praktikum.lumera.model.CartItem
// Jika folder atau nama package kamu berbeda, bagian ini akan tetap merah.
// Cukup hapus baris import yang merah, lalu ketik ulang "LoginScreen", dan tekan Alt + Enter untuk auto-import.
import com.praktikum.lumera.screens.home.HomeScreen
import com.praktikum.lumera.screens.cart.CartScreen
import com.praktikum.lumera.screens.payment.PaymentScreen
import com.praktikum.lumera.screens.receipt.ReceiptScreen

@Composable
fun AppNavigation() { // KOSONGKAN bagian dalam kurung ini
    val navController = rememberNavController()
    val cart = remember { mutableStateListOf<CartItem>() }

    NavHost(navController = navController, startDestination = "login") {
        composable("login") {
            LoginScreen(navController) // Ini memanggil fungsi, bukan parameter
        }
        composable("register") {
            RegisterScreen(navController)
        }

        composable("home") {
            HomeScreen(navController, cart)
        }

        composable("cart") {
            CartScreen(navController, cart)
        }

        composable("payment") {
            val total = cart.sumOf { it.menu.price * it.quantity }
            PaymentScreen(navController, total)
        }

        composable("receipt") {
            ReceiptScreen(cart, navController)
        }
    }
}

@Composable
fun RegisterScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}

@Composable
fun LoginScreen(x0: NavHostController) {
    TODO("Not yet implemented")
}