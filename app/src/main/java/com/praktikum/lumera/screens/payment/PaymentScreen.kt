package com.praktikum.lumera.screens.payment

import androidx.compose.foundation.layout.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaymentScreen(
    total: Int,
    onBack: () -> Unit,
    onPay: () -> Unit
) {

    val formatRupiah = NumberFormat.getInstance(Locale("in", "ID"))

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Start
        ) {
            IconButton(onClick = onBack) {
                Icon(Icons.Default.ArrowBack, contentDescription = "Back")
            }
        }

        Spacer(modifier = Modifier.height(16.dp))

        Text(
            text = "Payment",
            style = MaterialTheme.typography.headlineMedium
        )

        Spacer(modifier = Modifier.height(16.dp))

        Text("Total yang harus dibayar:")

        Text(
            text = "Rp ${formatRupiah.format(total)}",
            style = MaterialTheme.typography.headlineSmall
        )

        Spacer(modifier = Modifier.height(24.dp))

        Button(
            onClick = onPay,
            modifier = Modifier.fillMaxWidth()
        ) {
            Text("Bayar Sekarang")
        }
    }
}