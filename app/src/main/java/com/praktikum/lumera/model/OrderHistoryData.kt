package com.praktikum.lumera.data

import androidx.compose.runtime.mutableStateListOf

import com.praktikum.lumera.model.OrderHistory

object OrderHistoryData {

    val historyList = mutableStateListOf(

        OrderHistory(

            id = 1,

            title = "Caramel Latte",

            total = 56000,

            paymentMethod = "QRIS",

            date = "20 May 2026",

            status = "Completed"
        ),

        OrderHistory(

            id = 2,

            title = "Mocha Caramel",

            total = 42000,

            paymentMethod = "Cash",

            date = "18 May 2026",

            status = "Completed"
        ),

        OrderHistory(

            id = 3,

            title = "Tiramisu Cup",

            total = 25000,

            paymentMethod = "Debit",

            date = "16 May 2026",

            status = "Completed"
        )
    )
}