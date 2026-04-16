package com.praktikum.lumera.navigation

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.*
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.screens.home.HomeScreen
import com.praktikum.lumera.screens.cart.CartScreen
import com.praktikum.lumera.screens.payment.PaymentScreen
import com.praktikum.lumera.screens.receipt.ReceiptScreen

@Composable
fun AppNavigation() {
    val navController = rememberNavController()
    // State keranjang belanja
    val cart = remember { mutableStateListOf<CartItem>() }

    // startDestination diatur ke "login"
    NavHost(navController = navController, startDestination = "login") {

        composable("login") {
            LoginScreen(navController)
        }

        // Rute "register" sudah dihapus dari sini

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
fun LoginScreen(navController: NavHostController) {
    Column(
        modifier = Modifier.fillMaxSize().padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Text("Halaman Login")
        Spacer(modifier = Modifier.height(16.dp))

        Button(onClick = { navController.navigate("home") }) {
            Text("Masuk")
        }
    }
}
