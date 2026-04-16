package com.praktikum.lumera.screens.receipt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.praktikum.lumera.model.CartItem

@Composable
fun ReceiptScreen(cart: List<CartItem>, navController: NavController) {

    val total = cart.sumOf { it.menu.price * it.quantity }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text("Struk Pembayaran", style = MaterialTheme.typography.headlineMedium)

        Spacer(modifier = Modifier.height(16.dp))

        cart.forEach {
            Text("${it.menu.name} x${it.quantity} = Rp ${it.menu.price * it.quantity}")
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total: Rp $total")

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = { navController.navigate("home") },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selesai")
        }
    }
}