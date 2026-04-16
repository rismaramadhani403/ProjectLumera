package com.praktikum.lumera.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.praktikum.lumera.model.CartItem

@Composable
// UBAH: cart sekarang menggunakan MutableList agar bisa menghapus item (cart.remove)
fun CartScreen(navController: NavController, cart: MutableList<CartItem>) {

    val total = cart.sumOf { it.menu.price * it.quantity }

    Column(Modifier.padding(16.dp)) {

        Text("Keranjang", style = MaterialTheme.typography.headlineMedium)
        Spacer(modifier = Modifier.height(16.dp))

        // LazyColumn diberi weight(1f) agar mengambil sisa ruang kosong di layar
        // dan mendorong tombol "Bayar" ke bagian paling bawah
        LazyColumn(modifier = Modifier.weight(1f)) {

            // ===== INI ADALAH BAGIAN YANG DIGANTI =====
            items(cart) { item ->
                Row(
                    modifier = Modifier.fillMaxWidth().padding(8.dp),
                    horizontalArrangement = Arrangement.SpaceBetween,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Column {
                        Text(text = item.menu.name, style = MaterialTheme.typography.bodyLarge)
                        Text(text = "Jumlah: ${item.quantity}", style = MaterialTheme.typography.bodySmall)
                    }
                    IconButton(onClick = { cart.remove(item) }) {
                        Icon(imageVector = Icons.Default.Delete, contentDescription = "Hapus")
                    }
                }
            }
            // ==========================================

        }

        Spacer(modifier = Modifier.height(16.dp))
        Text("Total: Rp $total", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = { navController.navigate("payment") },
            modifier = Modifier.fillMaxWidth() // Tombol dibuat memenuhi lebar layar
        ) {
            Text("Bayar")
        }
    }
}