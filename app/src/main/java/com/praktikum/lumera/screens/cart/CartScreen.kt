package com.praktikum.lumera.screens.cart

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.praktikum.lumera.model.CartItem
import java.text.NumberFormat
import java.util.Locale

@Composable
fun CartScreen(
    cart: SnapshotStateList<CartItem>,
    onBack: () -> Unit,
    onCheckout: () -> Unit
) {
    val formatRupiah = remember { NumberFormat.getInstance(Locale("in", "ID")) }

    val total = remember(cart) {
        cart.sumOf { it.menu.price * it.quantity }
    }

    var selectedItem by remember { mutableStateOf<CartItem?>(null) }
    var showDialog by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(modifier = Modifier.fillMaxWidth()) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = "Keranjang",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        LazyColumn(
            modifier = Modifier.weight(1f),
            contentPadding = PaddingValues(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(
                items = cart,
                key = { it.menu.id }
            ) { item ->
                CartItemRow(
                    item = item,
                    onDelete = {
                        selectedItem = item
                        showDialog = true
                    },
                    formatRupiah = formatRupiah
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Total: Rp ${formatRupiah.format(total)}",
            style = MaterialTheme.typography.titleLarge
        )

        Spacer(modifier = Modifier.height(16.dp))

        Button(
            onClick = onCheckout,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Bayar")
        }
    }

    if (showDialog && selectedItem != null) {
        AlertDialog(
            onDismissRequest = { showDialog = false },
            title = { Text("Konfirmasi") },
            text = { Text("Yakin ingin menghapus item ini?") },
            confirmButton = {
                TextButton(
                    onClick = {
                        cart.remove(selectedItem)
                        showDialog = false
                    }
                ) {
                    Text("Ya")
                }
            },
            dismissButton = {
                TextButton(
                    onClick = { showDialog = false }
                ) {
                    Text("Batal")
                }
            }
        )
    }
}

@Composable
fun CartItemRow(
    item: CartItem,
    onDelete: () -> Unit,
    formatRupiah: NumberFormat
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(4.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(12.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(item.menu.name)
                Text("Jumlah: ${item.quantity}")
                Text("Rp ${formatRupiah.format(item.menu.price)}")
            }

            IconButton(onClick = onDelete) {
                Icon(Icons.Default.Delete, contentDescription = "Hapus")
            }
        }
    }
}