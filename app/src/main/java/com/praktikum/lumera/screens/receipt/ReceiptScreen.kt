package com.praktikum.lumera.screens.receipt

import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.praktikum.lumera.model.CartItem
import java.text.NumberFormat
import java.text.SimpleDateFormat
import java.util.*

@Composable
fun ReceiptScreen(
    cart: List<CartItem>,
    onFinish: () -> Unit,
    onBackToHome: () -> Unit
) {

    val formatRupiah = NumberFormat.getInstance(Locale("in", "ID"))
    val total = cart.sumOf { it.menu.price * it.quantity }

    val currentTime = SimpleDateFormat(
        "dd MMM yyyy HH:mm",
        Locale("id", "ID")
    ).format(Date())

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Text(
            text = "Struk Pembayaran",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            elevation = CardDefaults.cardElevation(6.dp)
        ) {

            Column(
                modifier = Modifier.padding(16.dp)
            ) {

                Text(
                    text = "Waktu: $currentTime",
                    style = MaterialTheme.typography.bodySmall
                )

                Spacer(modifier = Modifier.height(12.dp))

                cart.forEach {
                    Text(
                        "${it.menu.name} x${it.quantity} = Rp ${
                            formatRupiah.format(it.menu.price * it.quantity)
                        }"
                    )
                }

                Spacer(modifier = Modifier.height(12.dp))

                Divider()

                Spacer(modifier = Modifier.height(12.dp))

                Text(
                    text = "Total Pembayaran",
                    style = MaterialTheme.typography.bodyMedium
                )

                Text(
                    text = "Rp ${formatRupiah.format(total)}",
                    style = MaterialTheme.typography.headlineSmall,
                    color = MaterialTheme.colorScheme.primary
                )
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Terima kasih sudah memesan ☕",
            style = MaterialTheme.typography.bodyMedium
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onFinish,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Selesai")
        }

        Spacer(modifier = Modifier.height(8.dp))

        TextButton(
            onClick = onBackToHome,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Kembali ke Home")
        }
    }
}