package com.praktikum.lumera.screens.payment

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import com.praktikum.lumera.R
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.ui.theme.CreamBackground
import com.praktikum.lumera.ui.theme.LightGrayText
import com.praktikum.lumera.ui.theme.SoftBrownText
import com.praktikum.lumera.ui.theme.SoftCaramel
import com.praktikum.lumera.ui.theme.WarmWhite
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaymentScreen(

    total: Int,

    selectedPaymentFromCart: String,

    onBack: () -> Unit,

    onPay: (String, String) -> Unit
){

    val formatRupiah = NumberFormat.getInstance(

        Locale("in", "ID")
    )

    var selectedPayment by remember {

        mutableStateOf(selectedPaymentFromCart)
    }

    var customerName by remember {

        mutableStateOf(

            if (
                SessionManager.currentUser.value?.role
                == "Customer"
            ) {

                SessionManager.currentUser.value?.name ?: ""

            } else {

                ""
            }
        )
    }

    Column(

        modifier = Modifier
            .fillMaxSize()
            .verticalScroll(
                rememberScrollState()
            )
            .background(CreamBackground)
            .padding(20.dp)
    ) {

        // =========================
        // HEADER
        // =========================
        Row(

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(

                onClick = onBack
            ) {

                Icon(

                    Icons.Default.ArrowBack,

                    contentDescription = "Back",

                    tint = SoftBrownText
                )
            }

            Text(

                text = "Payment",

                style =
                    MaterialTheme.typography
                        .headlineMedium,

                color = SoftBrownText
            )
        }

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // TOTAL CARD
        // =========================
        Card(

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(24.dp),

            colors = CardDefaults.cardColors(

                containerColor =
                    Color(0xFFE8D2B3)
            )
        ) {

            Column(

                modifier = Modifier.padding(20.dp)
            ) {

                Text(

                    text = "Total Pembayaran",

                    color = LightGrayText
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(

                    text =
                        "Rp ${
                            formatRupiah.format(total)
                        }",

                    style =
                        MaterialTheme.typography
                            .headlineMedium,

                    color = SoftCaramel
                )
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // =========================
        // CUSTOMER NAME
        // =========================
        OutlinedTextField(

            value = customerName,

            onValueChange = {

                customerName = it
            },

            label = {

                Text("Nama Customer")
            },

            modifier = Modifier.fillMaxWidth(),

            shape = RoundedCornerShape(18.dp),

            singleLine = true
        )

        Spacer(
            modifier = Modifier.height(24.dp)
        )

        // =========================
        // PAYMENT TITLE
        // =========================
        Text(

            text = "Pilih Metode Pembayaran",

            style =
                MaterialTheme.typography
                    .titleMedium,

            color = SoftBrownText
        )

        Spacer(
            modifier = Modifier.height(16.dp)
        )

        // =========================
        // CASH
        // =========================
        PaymentMethodCard(

            title = "💵 Cash",

            selected =
                selectedPayment == "Cash",

            onClick = {

                selectedPayment = "Cash"
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // =========================
        // QRIS
        // =========================
        PaymentMethodCard(

            title = "📱 QRIS",

            selected =
                selectedPayment == "QRIS",

            onClick = {

                selectedPayment = "QRIS"
            }
        )

        Spacer(
            modifier = Modifier.height(14.dp)
        )

        // =========================
        // DEBIT
        // =========================
        PaymentMethodCard(

            title = "💳 Debit",

            selected =
                selectedPayment == "Debit",

            onClick = {

                selectedPayment = "Debit"
            }
        )

        Spacer(
            modifier = Modifier.height(20.dp)
        )

        // =========================
        // QRIS IMAGE
        // =========================
        if (selectedPayment == "QRIS") {

            Card(

                modifier = Modifier.fillMaxWidth(),

                shape = RoundedCornerShape(24.dp),

                colors = CardDefaults.cardColors(

                    containerColor =
                        Color(0xFFF3E2C7)
                )
            ) {

                Column(

                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(20.dp),

                    horizontalAlignment =
                        Alignment.CenterHorizontally
                ) {

                    Text(

                        text = "Scan QRIS",

                        style =
                            MaterialTheme.typography
                                .titleMedium,

                        color = SoftBrownText
                    )

                    Spacer(
                        modifier = Modifier.height(16.dp)
                    )

                    Image(

                        painter = painterResource(
                            id = R.drawable.qris
                        ),

                        contentDescription = "QRIS",

                        modifier = Modifier.size(220.dp),

                        contentScale = ContentScale.Fit
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // =========================
        // PAY BUTTON
        // =========================
        Button(

            onClick = {

                onPay(

                    selectedPayment,

                    customerName
                )
            },

            modifier = Modifier
                .fillMaxWidth()
                .height(58.dp),

            shape = RoundedCornerShape(22.dp),

            colors = ButtonDefaults.buttonColors(

                containerColor = SoftCaramel
            )
        ) {

            Text(

                text =
                    "Bayar dengan $selectedPayment",

                color = WarmWhite
            )
        }

        Spacer(
            modifier = Modifier.height(20.dp)
        )
    }
}

@Composable
fun PaymentMethodCard(

    title: String,

    selected: Boolean,

    onClick: () -> Unit
) {

    Card(

        modifier = Modifier
            .fillMaxWidth()
            .clickable {

                onClick()
            },

        shape = RoundedCornerShape(22.dp),

        colors = CardDefaults.cardColors(

            containerColor =

                if (selected)
                    SoftCaramel.copy(alpha = 0.15f)

                else
                    WarmWhite
        ),

        border =

            if (selected)

                ButtonDefaults.outlinedButtonBorder

            else

                null
    ) {

        Row(

            modifier = Modifier
                .fillMaxWidth()
                .padding(18.dp),

            verticalAlignment =
                Alignment.CenterVertically,

            horizontalArrangement =
                Arrangement.SpaceBetween
        ) {

            Text(

                text = title,

                style =
                    MaterialTheme.typography
                        .titleMedium,

                color = SoftBrownText
            )

            if (selected) {

                Icon(

                    Icons.Default.CheckCircle,

                    contentDescription = null,

                    tint = SoftCaramel
                )
            }
        }
    }
}