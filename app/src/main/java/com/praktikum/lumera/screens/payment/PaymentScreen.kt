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
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.praktikum.lumera.R
import com.praktikum.lumera.data.SessionManager
import com.praktikum.lumera.ui.theme.CreamBackground
import com.praktikum.lumera.ui.theme.SoftBrownText
import com.praktikum.lumera.ui.theme.SoftCaramel
import com.praktikum.lumera.ui.theme.WarmWhite
import java.text.NumberFormat
import java.util.Locale

@Composable
fun PaymentScreen(

    total: Int,

    customerName: String,

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

    val currentUser = SessionManager.currentUser.value

    var orderFor by remember {

        mutableStateOf(customerName)
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

            shape = RoundedCornerShape(20.dp)

        ) {

            Column(

                modifier = Modifier.padding(18.dp)

            ) {

                Text(
                    text = "Order For",
                    color = Color.Gray
                )

                if (currentUser?.role == "Customer") {

                    Text(

                        text = orderFor,

                        fontWeight = FontWeight.Bold,

                        fontSize = 18.sp
                    )

                } else {

                    OutlinedTextField(

                        value = orderFor,

                        onValueChange = {

                            orderFor = it
                        },

                        modifier = Modifier.fillMaxWidth(),

                        placeholder = {

                            Text("Masukkan nama customer")
                        },

                        singleLine = true
                    )
                }
            }
        }

        Spacer(
            modifier = Modifier.height(28.dp)
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

                    orderFor
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

            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Column {

                    Text(
                        text = "Total",
                        color = Color.White,
                        fontSize = 12.sp
                    )

                    Text(
                        text = "Rp ${formatRupiah.format(total)}",
                        color = Color.White,
                        fontWeight = FontWeight.Bold
                    )
                }

                Text(
                    text = "Pay →",
                    color = Color.White,
                    fontWeight = FontWeight.Bold,
                    fontSize = 18.sp
                )

            }

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