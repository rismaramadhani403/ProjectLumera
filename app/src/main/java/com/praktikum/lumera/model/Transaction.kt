package com.praktikum.lumera.model

data class Transaction(

    val customerName: String,

    val total: Int,

    val paymentMethod: String,

    val cashierName: String,

    val date: String
)