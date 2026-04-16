package com.praktikum.lumera.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.praktikum.lumera.model.CartItem

@Composable
fun CartScreen(navController: NavController, cart: List<CartItem>) {

    val total = cart.sumOf { it.menu.price * it.quantity }

    Column(Modifier.padding(16.dp)) {

        Text("Keranjang")

        LazyColumn {
            items(cart) {
                Text("${it.menu.name} x${it.quantity}")
            }
        }

        Text("Total: Rp $total")

        Button(onClick = { navController.navigate("payment") }) {
            Text("Bayar")
        }
    }
}