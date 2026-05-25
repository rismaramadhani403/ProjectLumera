package com.praktikum.lumera.screens.history

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items

import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack

import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable

import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

import androidx.compose.ui.draw.clip

import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color

import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.praktikum.lumera.data.OrderHistoryData
import com.praktikum.lumera.model.OrderHistory

import com.praktikum.lumera.utils.formatRupiah

@Composable
fun OrderHistoryScreen(

    onBack: () -> Unit
) {

    val histories =
        OrderHistoryData.historyList

    Column(

        modifier = Modifier
            .fillMaxSize()
            .background(

                Brush.verticalGradient(

                    colors = listOf(

                        Color(0xFF120B08),
                        Color(0xFF1A0F0A),
                        Color.Black
                    )
                )
            )
            .padding(20.dp)
    ) {

        Spacer(
            modifier = Modifier.height(30.dp)
        )

        // =========================
        // HEADER
        // =========================
        Row(

            modifier = Modifier.fillMaxWidth(),

            verticalAlignment =
                Alignment.CenterVertically
        ) {

            IconButton(

                onClick = {

                    onBack()
                },

                modifier = Modifier
                    .clip(CircleShape)
                    .background(
                        Color(0xFFD99A3E)
                    )
            ) {

                Icon(

                    imageVector =
                        Icons.AutoMirrored.Filled.ArrowBack,

                    contentDescription = null,

                    tint = Color.White
                )
            }

            Spacer(
                modifier = Modifier.padding(8.dp)
            )

            Text(

                text = "Order History",

                color = Color.White,

                fontSize = 28.sp,

                fontWeight = FontWeight.Bold
            )
        }

        Spacer(
            modifier = Modifier.height(28.dp)
        )

        // =========================
        // EMPTY STATE
        // =========================
        if (histories.isEmpty()) {

            Column(

                modifier = Modifier
                    .fillMaxSize(),

                verticalArrangement =
                    Arrangement.Center,

                horizontalAlignment =
                    Alignment.CenterHorizontally
            ) {

                Text(

                    text = "☕",

                    fontSize = 70.sp
                )

                Spacer(
                    modifier = Modifier.height(14.dp)
                )

                Text(

                    text = "No Order Yet",

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 24.sp
                )

                Spacer(
                    modifier = Modifier.height(8.dp)
                )

                Text(

                    text =
                        "Start ordering your favorite coffee",

                    color = Color.LightGray
                )
            }

        } else {

            LazyColumn(

                verticalArrangement =
                    Arrangement.spacedBy(18.dp)
            ) {

                items(histories) { history ->

                    OrderHistoryCard(
                        history = history
                    )
                }
            }
        }
    }
}

@Composable
fun OrderHistoryCard(

    history: OrderHistory
) {

    Card(

        modifier = Modifier
            .fillMaxWidth(),

        shape = RoundedCornerShape(28.dp),

        colors = CardDefaults.cardColors(

            containerColor =
                Color.White.copy(
                    alpha = 0.08f
                )
        )
    ) {

        Column(

            modifier = Modifier
                .fillMaxWidth()
                .padding(20.dp)
        ) {

            // =========================
            // TITLE + STATUS
            // =========================
            Row(

                modifier = Modifier.fillMaxWidth(),

                horizontalArrangement =
                    Arrangement.SpaceBetween,

                verticalAlignment =
                    Alignment.CenterVertically
            ) {

                Text(

                    text = history.title,

                    color = Color.White,

                    fontWeight = FontWeight.Bold,

                    fontSize = 20.sp
                )

                Box(

                    modifier = Modifier
                        .clip(
                            RoundedCornerShape(50.dp)
                        )
                        .background(
                            Color(0xFFD99A3E)
                        )
                        .padding(
                            horizontal = 14.dp,
                            vertical = 6.dp
                        )
                ) {

                    Text(

                        text = history.status,

                        color = Color.White,

                        fontSize = 12.sp,

                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(
                modifier = Modifier.height(14.dp)
            )

            // =========================
            // TOTAL
            // =========================
            Text(

                text =
                    formatRupiah(history.total),

                color = Color(0xFFD99A3E),

                fontWeight = FontWeight.Bold,

                fontSize = 24.sp
            )

            Spacer(
                modifier = Modifier.height(16.dp)
            )

            // =========================
            // PAYMENT
            // =========================
            Text(

                text =
                    "Payment : ${history.paymentMethod}",

                color = Color.LightGray
            )

            Spacer(
                modifier = Modifier.height(6.dp)
            )

            // =========================
            // DATE
            // =========================
            Text(

                text =
                    "Date : ${history.date}",

                color = Color.LightGray
            )
        }
    }
}