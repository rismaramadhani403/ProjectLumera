package com.praktikum.lumera.screens.receipt

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.model.CartItem
import com.praktikum.lumera.utils.formatRupiah
import java.text.SimpleDateFormat
import java.util.Date
import java.util.Locale

@Composable
fun ReceiptScreen(

    cart: List<CartItem>,

    paymentMethod: String,

    customerName: String,

    cashierName: String,

    subtotal: Int,

    discount: Int,

    deliveryFee: Int,

    total: Int,

    onFinish: () -> Unit,

    onBackToHome: () -> Unit
) {

    // =========================
    // FINAL TOTAL
    // =========================
    val finalTotal = total

    // =========================
    // CURRENT TIME
    // =========================
    val currentTime = SimpleDateFormat(

        "dd MMM yyyy HH:mm",

        Locale("id", "ID")
    ).format(Date())

    // =========================
    // INVOICE NUMBER
    // =========================
    val invoiceNumber =
        "INV-${System.currentTimeMillis()}"

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .background(
                Color(0xFFF7F3EE)
            )
            .padding(20.dp)
    ) {

        // =========================
        // TITLE
        // =========================
        Text(

            text = "Receipt",

            fontSize = 32.sp,

            fontWeight = FontWeight.Bold
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // RECEIPT CARD
        // =========================
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            elevation =
                CardDefaults.cardElevation(
                    8.dp
                )

        ) {

            Column(

                modifier = Modifier
                    .fillMaxWidth()
                    .background(Color.White)
                    .padding(20.dp)

            ) {

                // =========================
                // CAFE NAME
                // =========================
                Text(

                    text = "LUMERA CAFE ☕",

                    fontSize = 24.sp,

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(10.dp)
                )

                Divider()

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                // =========================
                // RECEIPT INFO
                // =========================
                ReceiptRow(

                    label = "No Nota",

                    value = invoiceNumber
                )

                ReceiptRow(

                    label = "Waktu",

                    value = currentTime
                )

                ReceiptRow(

                    label = "Customer",

                    value = customerName
                )

                // =========================
                // CASHIER
                // =========================
                if (cashierName.isNotEmpty()) {

                    ReceiptRow(

                        label = "Kasir",

                        value = cashierName
                    )
                }

                ReceiptRow(

                    label = "Metode",

                    value = paymentMethod
                )

                ReceiptRow(

                    label = "Status",

                    value = "Berhasil ✅"
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Divider()

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // =========================
                // CART ITEMS
                // =========================
                cart.forEach { item ->

                    Text(

                        text = item.menu.name,

                        fontWeight =
                            FontWeight.Bold
                    )

                    Spacer(
                        modifier = Modifier.height(4.dp)
                    )

                    Text(

                        text =
                            "${item.quantity} x ${
                                formatRupiah(
                                    item.menu.price
                                )
                            }"
                    )

                    Spacer(
                        modifier = Modifier.height(12.dp)
                    )
                }

                Divider()

                Spacer(
                    modifier = Modifier.height(18.dp)
                )

                // =========================
                // PAYMENT DETAIL
                // =========================
                ReceiptRow(

                    label = "Subtotal",

                    value = formatRupiah(subtotal)
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                ReceiptRow(

                    label = "Delivery",

                    value = formatRupiah(deliveryFee)
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                ReceiptRow(

                    label = "Discount",

                    value =
                        "-${formatRupiah(discount)}"
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                ReceiptRow(

                    label = "Total Bayar",

                    value =
                        formatRupiah(finalTotal)
                )

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                Divider()

                Spacer(
                    modifier = Modifier.height(20.dp)
                )

                // =========================
                // THANK YOU
                // =========================
                Text(

                    text = "Terima kasih ☕",

                    fontWeight = FontWeight.Bold
                )

                Spacer(
                    modifier = Modifier.height(6.dp)
                )

                Text(

                    text =
                        "@lumeracafe"
                )
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // =========================
        // FINISH BUTTON
        // =========================
        Button(

            onClick = onFinish,

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(20.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor =
                    Color(0xFFC68642)
            )
        ) {

            Text(

                text = "Selesai",

                color = Color.White,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(10.dp)
        )

        // =========================
        // BACK HOME BUTTON
        // =========================
        TextButton(

            onClick = onBackToHome,

            modifier = Modifier.fillMaxWidth()
        ) {

            Text(

                text = "Kembali ke Home"
            )
        }
    }
}

@Composable
fun ReceiptRow(

    label: String,

    value: String
) {

    Row(

        modifier = Modifier.fillMaxWidth()
    ) {

        Text(

            text = label,

            modifier = Modifier.width(110.dp)
        )

        Text(": ")

        Text(
            text = value
        )
    }
}